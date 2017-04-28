package com.sdi.client;

import com.sdi.client.action.DeshabilitarUsuarioAction;
import com.sdi.client.action.EliminarUsuarioAction;
import com.sdi.client.action.ListarUsuariosAction;

import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu{
	
	
	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrador", null },
			{ "Listar usuarios", 			ListarUsuariosAction.class},
			{ "Deshabilitar un usuario",	DeshabilitarUsuarioAction.class},
			{ "Eliminar usuario", 			EliminarUsuarioAction.class},
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

	/*public static void main(String[] args) {		
		AdminService as = new RemoteServicesLocator().getAdminService();
		try {
			List<User> usuarios = as.findAllUsers();
			System.out.println("Listando usuarios");
			for(User u: usuarios){
				System.out.println(u.toString());
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
