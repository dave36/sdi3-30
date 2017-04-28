package com.sdi.client.action;

import java.util.List;

import com.sdi.ws.admin.AdminService;
import com.sdi.ws.admin.EjbAdminServiceImplService;
import com.sdi.ws.admin.UserTask;

import alb.util.console.Console;
import alb.util.menu.Action;

public class ListarUsuariosAction implements Action {

	@Override
	public void execute() throws Exception {
		AdminService as = new EjbAdminServiceImplService().getAdminServicePort();
		List<UserTask> lista = as.findAllUserWithTasks();
		
		Console.println("Id\tLogin\tEmail\tIsAdmin\tStatus\tTCompletadas"
				+ "\tTCompletadas retrasadas\tTPlanificadas\tTNo planificadas");
		for(UserTask u: lista){
			imprimirUsuario(u);
		}
	}

	private void imprimirUsuario(UserTask u) {
		Console.println(u.getId() +"\t"+ u.getLogin()
				 +"\t"+ u.getEmail() +"\t"+ u.isAdmin()
				 +"\t"+ u.getStatus() +"\t"+ u.getTareasCompletadas()
				 +"\t"+ u.getTareasCompletadasRet() +"\t"+ u.getTareasPlanificadas()
				 +"\t"+ u.getTareasNoPlanificadas());
	}
}