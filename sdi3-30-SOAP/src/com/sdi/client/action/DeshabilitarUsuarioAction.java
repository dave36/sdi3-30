package com.sdi.client.action;

import java.util.List;
import com.sdi.ws.admin.AdminService;
import com.sdi.ws.admin.BusinessException_Exception;
import com.sdi.ws.admin.EjbAdminServiceImplService;
import com.sdi.ws.admin.User;

import alb.util.console.Console;
import alb.util.log.Log;
import alb.util.menu.Action;

public class DeshabilitarUsuarioAction implements Action {

	@Override
	public void execute() throws Exception {
		AdminService as = new EjbAdminServiceImplService().getAdminServicePort();
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
		} catch (BusinessException_Exception e) {
			Log.warn(e.getMessage());
		}
		Console.println("Id\tUsuario\tEmail\tStatus");
		for(User u: lista){
			Console.println(u.getId()+"\t"+u.getLogin()+"\t"+u.getEmail()+"\t"+
						u.getStatus());
		}
	}

}
