package com.sdi.client.action;

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

import com.sdi.client.util.Jndi;

import alb.util.console.Console;
import alb.util.log.Log;
import alb.util.menu.Action;

@MessageDriven(
		activationConfig = {
				@ActivationConfigProperty(
						propertyName="destination",
						propertyValue="jms/queue/recepcion"
				)
	})
public class VerTareasAction implements Action, MessageListener{
	
	private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String NOTANEITOR_QUEUE = "jms/queue/envio";

	private Session session = null;
	private MessageProducer sender = null;
	private Connection con = null;

	@Override
	public void execute() throws Exception {
		initialize();
		MapMessage map = createMessage();
		sender.send(map);
		close();

	}
	
	private MapMessage createMessage(){
		MapMessage map = null;
		try {
			map = session.createMapMessage();
			map.setString("command", "ver");
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

	@Override
	public void onMessage(Message msg) {
		try {
			process(msg);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void process(Message msg) throws JMSException {
		ObjectMessage map = (ObjectMessage)msg;
		@SuppressWarnings("unchecked")
		List<Object> lista = (List<Object>) map.getObject();
		Console.println(lista.get(0));
	}

	

}
