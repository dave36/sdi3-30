package com.sdi.client.action;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import com.sdi.client.model.Task;
import com.sdi.client.util.RestService;

import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.menu.Action;

public class VerTareasCategoriaAction implements Action {

	@Override
	public void execute() throws Exception {
		ListarCategoriasAction listar = new ListarCategoriasAction();
		listar.execute();
		Long idCategoria = Console.readLong("Id categoria");
		List<Task> lista = null;
		if (idCategoria != null) {
			lista = restGetTareasCategoria(idCategoria);
			printAtrasadas(lista);
			printPendientes(lista);
		} else {
			Console.println("Id incorrecto");
		}
	}

	private void printPendientes(List<Task> lista) {
		Console.println("--Tareas pendientes");
		Console.println("_ID__ ____TITULO____COMENTARIOS_______________"
				+ " _PLANEADA__");
		for (Task t : lista) {
			if (t.getPlanned() != null
					&& !DateUtil.isBefore(t.getPlanned(), DateUtil.now())) {
				Console.println(t.getId() + "\t" + t.getTitle() + "\t"
						+ t.getComments() + "\t" + t.getPlanned());
			}
			if (t.getPlanned() == null) {
				Console.println(t.getId() + "\t" + t.getTitle() + "\t"
						+ t.getComments() + "\t" + t.getPlanned());
			}
		}
	}

	private void printAtrasadas(List<Task> lista) {
		Console.println("--Tareas atrasadas");
		Console.println("_ID__ ____TITULO____COMENTARIOS_______________"
				+ " _PLANEADA__");
		for (Task t : lista) {
			if (t.getPlanned() != null
					&& DateUtil.isBefore(t.getPlanned(), DateUtil.now())) {
				Console.println(t.getId() + "\t" + t.getTitle() + "\t"
						+ t.getComments() + "\t" + t.getPlanned());
			}
		}
	}

	private List<Task> restGetTareasCategoria(Long idCategoria) {
		GenericType<List<Task>> lista = new GenericType<List<Task>>() {
		};

		List<Task> res = ClientBuilder.newClient()
				.target(RestService.REST_SERVICE_URL)
				.path("/TaskService/findTasks" + idCategoria).request().get()
				.readEntity(lista);
		return res;
	}
}
