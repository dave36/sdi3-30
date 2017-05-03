package com.sdi.business.integration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import com.sdi.business.TaskService;
import com.sdi.business.UserService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.Task;
import com.sdi.dto.User;
import com.sdi.infrastructure.Factories;

import alb.util.console.Console;
import alb.util.log.Log;

@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(
						propertyName = "destination",
						propertyValue = "jms/queue/envio")
})
public class GTDListener implements MessageListener {

	private TaskService taskService = Factories.services.getTaskService();
	private UserService userService = Factories.services.getUserService();

	@Override
	public void onMessage(Message msg) {
		Console.println("GTDListener: Msg received");
		try {
			initialize(NOTANEITOR_QUEUE_RESPUESTA);
			process(msg);
		} catch (JMSException e) {
			Log.warn(e);
		}
	}

	private void process(Message msg) throws JMSException {
		MapMessage map = (MapMessage) msg;

		User user = usuarioCorrecto(map.getString("usuario"),
				map.getString("contraseña"));

		if (user == null) {
			// Mensaje
			MapMessage msgresp = createResponseMessage("El usuario no existe");
			sender.send(msgresp);
			con.close();
			// Error a cola log
			initialize(NOTANEITOR_QUEUE_LOG);
			msgresp = createResponseMessage("Intento de loggin con "
					+ "usuario inexistente");
			sender.send(msgresp);
			con.close();
			return;
		}

		String cmd = map.getString("command");
		if ("ver".equals(cmd)) {
			doFindTasks(user.getId());
		} else if ("terminar".equals(cmd)) {
			doFinishTarea(map);
		} else if ("nueva".equals(cmd)) {
			doNewTarea(map, user.getId());
		} else {
			// Error a la cola del log
			initialize(NOTANEITOR_QUEUE_LOG);
			MapMessage msgresp = createResponseMessage("Comando desconocido");
			sender.send(msgresp);
			con.close();
		}
	}

	private User usuarioCorrecto(String user, String passwd)
			throws JMSException {
		try {
			User userLog = userService.findLoggableUser(user, passwd);
			return userLog;
		} catch (BusinessException e) {
			initialize(NOTANEITOR_QUEUE_LOG);
			MapMessage msgresp = createResponseMessage("Error al buscar al "
					+ "usuario en la base");
			sender.send(msgresp);
			con.close();
			Log.warn(e);
			return null;
		}
	}

	private void doFindTasks(Long userId) throws JMSException {
		try {
			List<Task> tareas = taskService.findTodayTasksByUserId(userId);
			ObjectMessage msg = createMessage(tareas);
			sender.send(msg);
		} catch (BusinessException e) {
			initialize(NOTANEITOR_QUEUE_LOG);
			MapMessage msgresp = createResponseMessage("Error al buscar las"
					+ " tareas del usuario");
			sender.send(msgresp);
			con.close();
			Log.warn(e);
		} finally {
			con.close();
		}
	}

	private void doFinishTarea(MapMessage map) throws JMSException {
		try {
			Task task = taskService.findTaskById(map.getLong("task_id"));
			if (task != null) {
				taskService.markTaskAsFinished(task.getId());
				MapMessage msg = createResponseMessage("Tarea finalizada");
				sender.send(msg);
			} else {
				// Informo del error al usuario
				MapMessage msg = createResponseMessage("La tarea no existe");
				sender.send(msg);
				// Al log
				initialize(NOTANEITOR_QUEUE_LOG);
				MapMessage msgresp = createResponseMessage("Se intento eliminar"
						+ " una tarea inexistente");
				sender.send(msgresp);
				con.close();
			}
		} catch (BusinessException e) {
			initialize(NOTANEITOR_QUEUE_LOG);
			MapMessage msgresp = createResponseMessage("Error al buscar la"
					+ " tarea en la base de datos");
			sender.send(msgresp);
			con.close();
			Log.warn(e);
		} finally {
			con.close();
		}
	}

	private void doNewTarea(MapMessage map, Long userId) throws JMSException {
		Task task = new Task();
		task.setTitle(map.getString("title"));
		task.setComments(map.getString("comments"));
		task.setUserId(userId);
		try {
			taskService.createTask(task);
			MapMessage msg = createResponseMessage("Tarea creada");
			sender.send(msg);
		} catch (BusinessException e) {
			initialize(NOTANEITOR_QUEUE_LOG);
			MapMessage msgresp = createResponseMessage("Error al crear tarea");
			sender.send(msgresp);
			con.close();
			Log.warn(e);
		} finally {
			con.close();
		}
	}

	private static final String JMS_CONNECTION_FACTORY = 
			"java:/ConnectionFactory";
	private static final String NOTANEITOR_QUEUE_RESPUESTA = 
			"jms/queue/recepcion";
	private static final String NOTANEITOR_QUEUE_LOG = 
			"jms/queue/log";

	private Session session = null;
	private MessageProducer sender = null;
	private Connection con = null;

	public void initialize(String cola) throws JMSException {
		ConnectionFactory factory = (ConnectionFactory) Jndi
				.find(JMS_CONNECTION_FACTORY);
		Destination queue = (Destination) Jndi.find(cola);
		con = factory.createConnection("sdi", "password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		sender = session.createProducer(queue);
		con.start();
	}

	private ObjectMessage createMessage(List<Task> lista) {
		ObjectMessage map = null;
		try {
			List<String> tareas = new ArrayList<>();
			for (Task t : lista) {
				tareas.add(t.toStringMensaje());
			}
			map = session.createObjectMessage();
			map.setObject((Serializable) tareas);
		} catch (JMSException e) {
			Log.warn(e);
		}
		return map;
	}

	private MapMessage createResponseMessage(String mensaje) {
		MapMessage map = null;
		try {
			map = session.createMapMessage();
			map.setString("mensaje", mensaje);
		} catch (JMSException e) {
			Log.warn(e);
		}
		return map;
	}
}
