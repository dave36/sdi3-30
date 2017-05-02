package com.sdi.client.action;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.sdi.client.util.Jndi;

import alb.util.console.Console;
import alb.util.log.Log;
import alb.util.menu.Action;

public class FinalizarTareaAction implements Action {
	
	private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String NOTANEITOR_QUEUE = "jms/queue/envio";

	private Session session = null;
	private MessageProducer sender = null;
	private Connection con = null;

	@Override
	public void execute() throws Exception {
		VerTareasAction action = new VerTareasAction();
		action.execute();
		Long id = Console.readLong("Id de la tarea");
		if(id!=null){
			initialize();
			MapMessage msg = createMessage(id);
			sender.send(msg);
			close();
			Console.println("La tarea con id " + id + " ha sido finalizada");
		}
	}
	
	private MapMessage createMessage(Long id) {
		MapMessage map = null;
		try {
			map = session.createMapMessage();
			map.setString("command", "terminar");
			map.setLong("task_id", id);
		} catch (JMSException e) {
			Log.warn(e);
		}
		return map;
	}

	public void initialize() throws JMSException {
		ConnectionFactory factory = (ConnectionFactory) Jndi
				.find(JMS_CONNECTION_FACTORY);
		Destination queue = (Destination) Jndi.find(NOTANEITOR_QUEUE);
		con = factory.createConnection("sdi", "password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		sender = session.createProducer(queue);
		con.start();
	}
	
	private void close() throws JMSException{
		sender.close();
		session.close();
		con.close();
	}
}
