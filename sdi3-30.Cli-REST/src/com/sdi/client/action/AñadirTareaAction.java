package com.sdi.client.action;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.sdi.client.model.Task;
import com.sdi.client.util.RestService;
import com.sdi.client.util.Session;

import alb.util.console.Console;
import alb.util.menu.Action;

public class AñadirTareaAction implements Action {

	@Override
	public void execute() throws Exception {
		ListarCategoriasAction action = new ListarCategoriasAction();
		action.execute();
		Long idCategoria = Console.readLong("Id categoria");
		Task tarea = crearTarea(Session.getInstance().getUserSession().getId(),
				idCategoria);
		createNewTask(tarea);
		Console.println("Tarea añadida");
	}

	private Task crearTarea(Long idUser, Long idCategoria) {
		String titulo = Console.readString("Titulo");
		String comentario = Console.readString("Comentario");
		Task task = new Task();
		task.setTitle(titulo);
		task.setComments(comentario);
		task.setCategoryId(idCategoria);
		task.setUserId(idUser);
		return task;
	}

	private Task createNewTask(Task task) {
		ClientBuilder.newClient().target(RestService.REST_SERVICE_URL)
				.path("/TaskService/createTask").request()
				.put(Entity.entity(task, MediaType.APPLICATION_JSON));
		return task;
	}
}
