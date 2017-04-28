package com.sdi.client.action;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.sdi.client.model.Task;
import com.sdi.client.util.RestService;

import alb.util.console.Console;
import alb.util.menu.Action;

public class FinalizarTareaAction implements Action{

	@Override
	public void execute() throws Exception {
		VerTareasCategoriaAction action = new VerTareasCategoriaAction();
		action.execute();
		Long id = Console.readLong("Id tarea");
		if(id!=null){
			finishTask(id);
			Console.println("La tarea " + id + " ha sido finalizada");
		}
		else{
			Console.println("Id de tarea incorrecto");
		}
	}
	
	private void finishTask(Long id){
		ClientBuilder.newClient()
			.target(RestService.REST_SERVICE_URL)
			.path("/TaskService/finishTask"+id)
			.request()
			.put(Entity.entity(Task.class, MediaType.APPLICATION_JSON));
	}
}
