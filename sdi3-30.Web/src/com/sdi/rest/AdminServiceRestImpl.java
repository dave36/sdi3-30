package com.sdi.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import alb.util.log.Log;

import com.sdi.business.AdminService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.User;
import com.sdi.infrastructure.Factories;

public class AdminServiceRestImpl implements AdminServiceRest {
	
	AdminService adminService = Factories.services.getAdminService();

	@Override
	public void deepDeleteUser(Long id) throws EntityNotFoundException {
		try {
			adminService.deepDeleteUser(id);
		} catch (BusinessException e) {
			Log.warn(e);
		}
	}

	@Override
	public void disableUser(Long id) throws EntityNotFoundException {
		try {
			adminService.disableUser(id);
		} catch (BusinessException e) {
			Log.warn(e);
		}
	}

	@Override
	public void enableUser(Long id) throws EntityNotFoundException {
		try {
			adminService.enableUser(id);
		} catch (BusinessException e) {
			Log.warn(e);
		}
	}

	@Override
	public List<User> findAllUsers() {
		try {
			return adminService.findAllUsers();
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public User findUserById(Long id) throws EntityNotFoundException {
		try {
			return adminService.findUserById(id);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public void dropAndInsert() {
		try {
			adminService.dropAndInsert();
		} catch (BusinessException e) {
			Log.warn(e);
		}
	}

}
