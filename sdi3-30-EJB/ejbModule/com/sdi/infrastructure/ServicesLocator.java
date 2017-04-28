package com.sdi.infrastructure;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.AdminService;
import com.sdi.business.TaskService;
import com.sdi.business.UserService;

public class ServicesLocator implements ServiceFactory {

	@Override
	public AdminService getAdminService() {
		InitialContext context;
		try {
			context = new InitialContext();
			return (AdminService)context.lookup("java:global/sdi3-30-EAR/sdi3-"
					+ "30-EJB/EjbAdminServiceImpl!com.sdi.business"
					+ ".impl.admin.AdminServiceLocal");
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public UserService getUserService() {
		InitialContext context;
		try {
			context = new InitialContext();
			return (UserService)context.lookup("java:global/sdi3-30-EAR/sdi3-30"
					+ "-EJB/EjbUserServiceImpl!com.sdi."
					+ "business.impl.user.UserServiceLocal");
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public TaskService getTaskService() {
		InitialContext context;
		try {
			context = new InitialContext();
			return (TaskService)context.lookup("java:global/sdi3-30-EAR/sdi3-30"
					+ "-EJB/EjbTaskServiceImpl!com.sdi."
					+ "business.impl.task.TaskServiceLocal");
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}
}
