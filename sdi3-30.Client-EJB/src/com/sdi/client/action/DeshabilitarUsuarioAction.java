package com.sdi.client.action;

import java.util.List;

import com.sdi.business.AdminService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.User;
import com.sdi.infrastructure.RemoteServicesLocator;

import alb.util.console.Console;
import alb.util.log.Log;
import alb.util.menu.Action;

public class DeshabilitarUsuarioAction implements Action {

	@Override
	public void execute() throws Exception {
		AdminService as = new RemoteServicesLocator().getAdminService();
		listarUsuarios(as);
		Long id = Console.readLong("Deshabilitar usuario con Id");
		if(id!=null){
			as.disableUser(id);
			Console.println("El usuario con Id="+id+" ha sido deshabilitado");
		}

	}

	private void listarUsuarios(AdminService as) {
		List<User> lista = null;
		try {
			lista = as.findAllUsers();
		} catch (BusinessException e) {
			Log.warn(e.getMessage());
		}
		Console.println("Id\tUsuario\tEmail\tStatus");
		for(User u: lista){
			Console.println(u.getId()+"\t"+u.getLogin()+"\t"+u.getEmail()+"\t"+
						u.getStatus());
		}
	}

}
