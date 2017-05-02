package com.sdi.business.integration;

import java.io.Serializable;
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
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.Task;
import com.sdi.infrastructure.Factories;

import alb.util.console.Console;
import alb.util.log.Log;

@MessageDriven(
	activationConfig = {
			@ActivationConfigProperty(
					propertyName="destination",
					propertyValue="jms/queue/envio"
			)
})
public class GTDListener implements MessageListener {
	
	
	private TaskService taskService = Factories.services.getTaskService();

	@Override
	public void onMessage(Message msg) {
		Console.println("GTDListener: Msg received");
		try {
			process(msg);
		} catch (JMSException e) {
			Log.warn(e);
		}
	}

	private void process(Message msg) throws JMSException {
		MapMessage map = (MapMessage) msg;
		
		String cmd = map.getString("command");
		if("ver".equals(cmd)){
			doFindTasks(new Long(12));
		}
		else if("terminar".equals(cmd)){
			doFinishTarea(map);
		}
		else if("nueva".equals(cmd)){
			doNewTarea(map);
		}
	}

	private void doFindTasks(Long long1) throws JMSException {
		try {
			List<Task> tareas = taskService.findTodayTasksByUserId(new Long(12));
			initialize();
			ObjectMessage msg = createMessage(tareas);
			sender.send(msg);
			close();
			
		} catch (BusinessException e) {
			Log.warn(e);
		}
	}

	private void doFinishTarea(MapMessage map) throws JMSException {
		try {
			taskService.markTaskAsFinished(map.getLong("task_id"));
		} catch (BusinessException e) {
			Log.warn(e);
		}		
	}

	private void doNewTarea(MapMessage map) throws JMSException {
		Task task = new Task();
		task.setTitle(map.getString("title"));
		task.setComments(map.getString("comments"));
		task.setUserId(map.getLong("user_id"));
		try {
			taskService.createTask(task);
		} catch (BusinessException e) {
			Log.warn(e);
		}
	}
	
	
	
	private static final String JMS_CONNECTION_FACTORY = "java:/ConnectionFactory";
	private static final String NOTANEITOR_QUEUE = "jms/queue/recepcion";

	private Session session = null;
	private MessageProducer sender = null;
	private Connection con = null;
	
	
	public void initialize() throws JMSException {
		ConnectionFactory factory = (ConnectionFactory) Jndi
				.find(JMS_CONNECTION_FACTORY);
		Destination queue = (Destination) Jndi.find(NOTANEITOR_QUEUE);
		con = factory.createConnection("sdi", "password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		sender = session.createProducer(queue);
		con.start();
	}

	private ObjectMessage createMessage(List<Task> lista) {
		ObjectMessage map = null;
		try {
			map = session.createObjectMessage();
			map.setObject((Serializable) lista);;
		} catch (JMSException e) {
			Log.warn(e);
		}
		return map;
	}
	
	private void close() throws JMSException{
		sender.close();
		session.close();
		con.close();
	}
}
