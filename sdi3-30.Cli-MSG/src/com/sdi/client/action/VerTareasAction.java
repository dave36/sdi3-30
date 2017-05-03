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

import alb.util.log.Log;
import alb.util.menu.Action;

public class VerTareasAction implements Action {

	private static final String JMS_CONNECTION_FACTORY = 
			"jms/RemoteConnectionFactory";

	private Session session = null;
	private MessageProducer sender = null;
	private Connection con = null;

	@Override
	public void execute() throws Exception {
		initialize("jms/queue/envio");
		MapMessage map = createMessage();
		sender.send(map);
		con.close();
		initialize("jms/queue/recepcion");
		con.close();
	}

	private MapMessage createMessage() {
		MapMessage map = null;
		try {
			map = session.createMapMessage();
			map.setString("command", "ver");
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
}
