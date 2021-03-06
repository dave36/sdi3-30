package com.sdi.client.util;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import alb.util.console.Console;

public class AuditMessageListener implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		try {
			processMessage(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private void processMessage(Message msg) throws JMSException{
		if((msg instanceof ObjectMessage)){
			ObjectMessage obj = (ObjectMessage)msg;
			@SuppressWarnings("unchecked")
			List<String> lista = (List<String>) obj.getObject();
			Util.printLista(lista);
			return;
		}
		else if((msg instanceof MapMessage)){
			MapMessage map = (MapMessage)msg;
			String mensaje = map.getString("mensaje");
			Util.printMensaje(mensaje);
		}
		else{
			Console.println("Mensaje no esperado");
		}
		
	}

}
