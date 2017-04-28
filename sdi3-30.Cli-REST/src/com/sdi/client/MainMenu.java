package com.sdi.client;

import com.sdi.client.action.AñadirTareaAction;
import com.sdi.client.action.FinalizarTareaAction;
import com.sdi.client.action.ListarCategoriasAction;
import com.sdi.client.action.VerTareasCategoriaAction;

import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu{
	
	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrador", null },
			{ "Listar categorias", 			ListarCategoriasAction.class},
			{ "Ver tareas",					VerTareasCategoriaAction.class},
			{ "Finalizar tarea", 			FinalizarTareaAction.class},
			{ "Añadir tarea",				AñadirTareaAction.class},
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}
	
}
