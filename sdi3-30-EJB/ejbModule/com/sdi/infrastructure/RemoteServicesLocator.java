package com.sdi.infrastructure;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.AdminService;
import com.sdi.business.TaskService;
import com.sdi.business.UserService;

public class RemoteServicesLocator implements ServiceFactory {

	@Override
	public AdminService getAdminService() {
		System.out.println("Using remote services locator");
		InitialContext context;
		try {
			context = new InitialContext();
			/*
			return (AdminService)context.lookup("sdi3-30-EAR/sdi3-"
					+ "30-EJB/EjbAdminServiceImpl!com.sdi.business"
					+ ".impl.admin.AdminServiceRemote");
					*/
			return (AdminService)context.lookup("sdi3-30-EAR/sdi3-30-EJB/EjbAdminServiceImpl!com.sdi.business.impl.admin.AdminServiceRemote");
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public UserService getUserService() {
		System.out.println("Using remote services locator");
		InitialContext context;
		try {
			context = new InitialContext();
			return (UserService)context.lookup("sdi3-30-EAR/sdi3-30"
					+ "-EJB/EjbUserServiceImpl!com.sdi."
					+ "business.impl.user.UserServiceRemote");
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public TaskService getTaskService() {
		System.out.println("Using remote services locator");
		InitialContext context;
		try {
			context = new InitialContext();
			return (TaskService)context.lookup("sdi3-30-EAR/sdi3-30"
					+ "-EJB/EjbTaskServiceImpl!com.sdi."
					+ "business.impl.task.TaskServiceRemote");
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}
}
