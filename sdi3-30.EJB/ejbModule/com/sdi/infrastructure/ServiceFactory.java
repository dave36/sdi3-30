package com.sdi.infrastructure;

import com.sdi.business.AdminService;
import com.sdi.business.TaskService;
import com.sdi.business.UserService;

public interface ServiceFactory {
	
	public AdminService getAdminService();
	public UserService getUserService();
	public TaskService getTaskService();
}