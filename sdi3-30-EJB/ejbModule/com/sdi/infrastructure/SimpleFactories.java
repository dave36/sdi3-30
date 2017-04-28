package com.sdi.infrastructure;

import com.sdi.business.AdminService;
import com.sdi.business.TaskService;
import com.sdi.business.UserService;
import com.sdi.business.impl.admin.EjbAdminServiceImpl;
import com.sdi.business.impl.task.EjbTaskServiceImpl;
import com.sdi.business.impl.user.EjbUserServiceImpl;



/**
 * Esta clase es la que realemente relaciona las interfaces de las capas 
 * con sus implementaciones finales. Si se deben hacer cambios de implementaci��n
 * en algunas de las capas habr��a que retocar esta clase.
 * 
 * En desarrollos mas sofisticados esto se especificar��n en ficheros de 
 * configuraci��n lo que permitiria al "assembler" y "deployer" ajustar los 
 * ensamblajes entre capas sin necesidad de recompilar.
 * 		Assembler: forma la aplicaci��n a base de componentes 
 * 			desarrollados externamente
 * 		Deployer: Adapta la aplicaci��n, o reconfigura la aplicaci��n en 
 * 			explotaci��n a las m��quinas/contenedores (tiers/layers)
 * 
 * Hay frameworks especializados en eso precisamente, por ejemplo Spring.
 * 
 * @author Enrique
 *
 */
public class SimpleFactories implements ServiceFactory {

	public AdminService getAdminService() {
		return new EjbAdminServiceImpl();
	}

	public UserService getUserService() {
		return new EjbUserServiceImpl();
	}

	public TaskService getTaskService() {
		return new EjbTaskServiceImpl();
	}
}
