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

public class AñadirTareaAction implements Action {

	private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String NOTANEITOR_QUEUE = "jms/queue/envio";

	private Session session = null;
	private MessageProducer sender = null;
	private Connection con = null;

	@Override
	public void execute() throws Exception {
		String usuario = Console.readString("Usuario");
		String contraseña = Console.readString("Constraseña");
		String title = Console.readString("Titulo");
		String comentarios = Console.readString("Comentarios");
		initialize("jms/queue/envio");
		MapMessage msg = createMessage(usuario, contraseña, title, comentarios);
		sender.send(msg);
		con.close();
		Console.println("Tarea añadida");
	}

	private MapMessage createMessage(String usuario, String contraseña,
			String title, String comentarios) {
		MapMessage map = null;
		try {
			map = session.createMapMessage();
			map.setString("usuario", usuario);
			map.setString("passwd", contraseña);
			map.setString("title", title);
			map.setString("comments", comentarios);
			map.setString("command", "nueva");
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
		if (cola.equals("jms/queue/envio")) {
			sender = session.createProducer(queue);
		} else {
			MessageConsumer consumer = session.createConsumer(queue);
			consumer.setMessageListener(new AuditMessageListener());
		}
		con.start();
	}

	private void close() throws JMSException {
		sender.close();
		session.close();
		con.close();
	}
}
