package com.sdi.client.action;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.client.model.Category;
import com.sdi.client.util.RestService;
import com.sdi.client.util.Session;

public class ListarCategoriasAction implements Action {

	@Override
	public void execute() throws Exception {
		List<Category> lista = restGetCategorias(Session.getInstance()
				.getUserSession().getId());
		Console.println("_ID__" + "____NOMBRE___");
		for (Category c : lista) {
			Console.println(c.getId() + "\t" + c.getName());
		}
	}

	private List<Category> restGetCategorias(Long id) {
		GenericType<List<Category>> lista = new GenericType<List<Category>>() {
		};

		List<Category> res = ClientBuilder.newClient()
				.target(RestService.REST_SERVICE_URL)
				.path("/TaskService/listaCategorias" + id).request().get()
				.readEntity(lista);
		return res;
	}
}
