package com.sdi.client.action;

import java.util.List;

import com.sdi.business.AdminService;
import com.sdi.dto.UserTask;
import com.sdi.infrastructure.RemoteServicesLocator;

import alb.util.console.Console;
import alb.util.menu.Action;

public class ListarUsuariosAction implements Action {

	@Override
	public void execute() throws Exception {
		AdminService as = new RemoteServicesLocator().getAdminService();
		List<UserTask> lista = as.findAllUserWithTasks();
		
		Console.println("Id\tLogin\tEmail\tIsAdmin\tStatus\tTCompletadas"
				+ "\tTCompletadas retrasadas\tTPlanificadas\tTNo planificadas");
		for(UserTask u: lista){
			imprimirUsuario(u);
		}
	}

	private void imprimirUsuario(UserTask u) {
		Console.println(u.getUser().getId() +"\t"+ u.getUser().getLogin()
				 +"\t"+ u.getUser().getEmail() +"\t"+ u.getUser().getIsAdmin()
				 +"\t"+ u.getUser().getStatus() +"\t"+ u.getTareasCompletadas()
				 +"\t"+ u.getTareasCompletadasRet() +"\t"+ u.getTareasPlanificadas()
				 +"\t"+ u.getTareasNoPlanificadas());
	}

}
