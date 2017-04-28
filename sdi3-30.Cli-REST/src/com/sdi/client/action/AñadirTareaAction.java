package com.sdi.client.action;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.sdi.client.model.Task;
import com.sdi.client.util.RestService;

import alb.util.console.Console;
import alb.util.menu.Action;

public class AñadirTareaAction implements Action{

	@Override
	public void execute() throws Exception {
		Task tarea = crearTarea(new Long(11));
		createNewTask(tarea);
	}

	private Task crearTarea(Long id) {
		String titulo = Console.readString("Titulo");
		String comentario = Console.readString("Comentario");
		Task task = new Task();
		task.setTitle(titulo);
		task.setComments(comentario);
		task.setUserId(id);
		return task;
	}
	
	private Task createNewTask(Task task){
		ClientBuilder.newClient()
			.target(RestService.REST_SERVICE_URL)
			.request()
			.put(Entity.entity(task, MediaType.APPLICATION_JSON));
		return task;
	}
}
