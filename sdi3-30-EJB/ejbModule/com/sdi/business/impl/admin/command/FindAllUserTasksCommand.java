package com.sdi.business.impl.admin.command;

import java.util.List;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.Command;
import com.sdi.dto.UserTask;
import com.sdi.persistence.Persistence;
import com.sdi.persistence.UserDao;

public class FindAllUserTasksCommand implements Command<List<UserTask>>{
	
	public FindAllUserTasksCommand(){
	}

	@Override
	public List<UserTask> execute() throws BusinessException {
		UserDao dao = Persistence.getUserDao();
		return dao.findAllUsersWithTasks();
	}
}
