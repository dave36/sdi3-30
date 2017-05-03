package com.sdi.client.action;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.sdi.client.util.AuditMessageListener;
import com.sdi.client.util.Jndi;

import alb.util.console.Console;
import alb.util.log.Log;
import alb.util.menu.Action;

public class FinalizarTareaAction implements Action {
	
	private static final String JMS_CONNECTION_FACTORY = 
			"jms/RemoteConnectionFactory";
	
	private static final String JMS_QUEUE_ENVIO = 
			"jms/queue/envio";
	
	private static final String JMS_QUEUE_RECEPCION = 
			"jms/queue/recepcion";

	private Session session = null;
	private MessageProducer sender = null;
	private Connection con = null;

	@Override
	public void execute() throws Exception {
		VerTareasAction action = new VerTareasAction();
		action.execute();
		String usuario = Console.readString("Usuario");
		String contraseña = Console.readString("Contraseña");
		Long id = Console.readLong("Id de la tarea");
		initialize(JMS_QUEUE_ENVIO);
		MapMessage msg = createMessage(usuario, contraseña, id);
		sender.send(msg);
		con.close();
		initialize(JMS_QUEUE_RECEPCION);
		con.close();
	}
	
	private MapMessage createMessage(String usuario, String contraseña, 
			Long id) {
		MapMessage map = null;
		try {
			map = session.createMapMessage();
			map.setString("command", "terminar");
			map.setString("usuario", usuario);
			map.setString("contraseña", contraseña);
			map.setLong("task_id", id);
		} catch (JMSException e) {
			Log.warn(e);
		}
		return map;
	}

	public void initialize(String cola) throws JMSException {
		ConnectionFactory factory = (ConnectionFactory) Jndi
				.find(JMS_CONNECTION_FACTORY);
		Destination queue = (Destination) Jndi.find(cola);
		con = factory.createConnection("sdi", "password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		if (cola.equals(JMS_QUEUE_ENVIO)) {
			sender = session.createProducer(queue);
		} else {
			MessageConsumer consumer = session.createConsumer(queue);
			consumer.setMessageListener(new AuditMessageListener());
		}
		con.start();
	}
}
