package com.sdi.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import alb.util.log.Log;

import com.sdi.business.TaskService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.Category;
import com.sdi.dto.Task;
import com.sdi.infrastructure.Factories;

public class TaskServiceRestImpl implements TaskServiceRest {
	
	TaskService services = Factories.services.getTaskService();

	@Override
	public List<Category> findCategoriesByUserId(Long id)
			throws EntityNotFoundException {
		try {
			return services.findCategoriesByUserId(id);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public Long createTask(Task task) throws EntityNotFoundException {
		try {
			return services.createTask(task);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public void markTaskAsFinished(Long id) throws EntityNotFoundException {
		try {
			services.markTaskAsFinished(id);
		} catch (BusinessException e) {
			Log.warn(e);
		}
	}

	@Override
	public List<Task> findTasksByCategoryId(Long catId)
			throws EntityNotFoundException {
		try {
			return services.findTasksByCategoryId(catId);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}
}
