package com.sdi.business.impl.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.admin.command.DeepDeleteUserCommand;
import com.sdi.business.impl.admin.command.DisableUserCommand;
import com.sdi.business.impl.admin.command.DropAndInsertDB;
import com.sdi.business.impl.admin.command.EnableUserCommand;
import com.sdi.business.impl.admin.command.FindAllUserTasksCommand;
import com.sdi.dto.User;
import com.sdi.dto.UserTask;
import com.sdi.persistence.Persistence;

@Stateless
@WebService(name="AdminService")
public class EjbAdminServiceImpl implements AdminServiceLocal, AdminServiceRemote {
	
	@Override
	public void deepDeleteUser(Long id) throws BusinessException {
		new DeepDeleteUserCommand( id ).execute();
	}

	@Override
	public void disableUser(Long id) throws BusinessException {
		new DisableUserCommand( id ).execute();
	}

	@Override
	public void enableUser(Long id) throws BusinessException {
		new EnableUserCommand( id ).execute();
	}

	@Override
	public List<User> findAllUsers() throws BusinessException {
		return Persistence.getUserDao().findAll();
	}

	@Override
	public User findUserById(final Long id) throws BusinessException {
		return Persistence.getUserDao().findById(id);
	}

	@Override
	public void dropAndInsert() throws BusinessException {
		new DropAndInsertDB().execute();		
	}

	@Override
	public List<UserTask> findAllUserWithTasks() throws BusinessException {
		return new FindAllUserTasksCommand().execute();
	}
}
