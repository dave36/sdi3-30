package com.sdi.client.util;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

public class Util {

	private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String NOTANEITOR_QUEUE = "jms/queue/envio";

	private Session session = null;
	private MessageProducer sender = null;
	private Connection con = null;

	public void initialize() throws JMSException {
		ConnectionFactory factory = (ConnectionFactory) Jndi
				.find(JMS_CONNECTION_FACTORY);
		Destination queue = (Destination) Jndi.find(NOTANEITOR_QUEUE);
		con = factory.createConnection("sdi", "password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		setSender(session.createProducer(queue));
		con.start();
	}

	public MessageProducer getSender() {
		return sender;
	}

	public void setSender(MessageProducer sender) {
		this.sender = sender;
	}
}
