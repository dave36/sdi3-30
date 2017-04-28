package com.sdi.client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.sdi.client.model.User;
import com.sdi.client.util.RestService;
import com.sdi.client.util.Session;

import alb.util.console.Console;

public class MainLogin {

	public static void main(String[] args) {
		new MainLogin().run();
	}

	private void run() {
		Console.println("Introduzca sus credenciales");
		User user = null;
		while (user == null) {
			String nombre = Console.readString("Usuario");
			String contraseña = Console.readString("Contraseña");
			user = login(nombre, contraseña);
			Session.getInstance().setUserSession(user);
		}
		MainMenu.main(null);
	}

	private User login(String login, String password) {
		return ClientBuilder.newClient().target(RestService.REST_SERVICE_URL)
				.path("/UserService/login" + login + "&" + password).request()
				.accept(MediaType.APPLICATION_JSON).get()
				.readEntity(User.class);
	}
}
