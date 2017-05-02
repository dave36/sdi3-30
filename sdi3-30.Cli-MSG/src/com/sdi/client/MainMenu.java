package com.sdi.client;

import com.sdi.client.action.AñadirTareaAction;
import com.sdi.client.action.FinalizarTareaAction;
import com.sdi.client.action.VerTareasAction;

import alb.util.console.Console;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu{
	public MainMenu() {
		Console.println("Opciones:");
		menuOptions = new Object[][] {
				{ "Añadir tarea",  AñadirTareaAction.class},
				{ "Finalizar tarea", FinalizarTareaAction.class},
				{ "Ver tareas de Hoy y atrasadas", VerTareasAction.class}				
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}
}
