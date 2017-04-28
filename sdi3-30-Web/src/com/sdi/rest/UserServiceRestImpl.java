package com.sdi.rest;

import javax.persistence.EntityNotFoundException;

import alb.util.log.Log;

import com.sdi.business.UserService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.User;
import com.sdi.infrastructure.Factories;

public class UserServiceRestImpl implements UserServiceRest {
	
	UserService service = Factories.services.getUserService();
	
	@Override
	public Long registerUser(User user) throws EntityNotFoundException {
		try {
			return service.registerUser(user);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public void updateUserDetails(User user) throws EntityNotFoundException {
		try {
			service.updateUserDetails(user);
		} catch (BusinessException e) {
			Log.warn(e);		
		}
	}

	@Override
	public User findLoggableUser(String login, String password)
			throws EntityNotFoundException {
		try {
			return service.findLoggableUser(login, password);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public User findRepeatUser(String user) throws EntityNotFoundException {
		try {
			return service.findRepeatUser(user);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

}
