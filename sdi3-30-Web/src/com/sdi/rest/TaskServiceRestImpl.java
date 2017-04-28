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
	public Long createCategory(Category category)
			throws EntityNotFoundException {
		try {
			return services.createCategory(category);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public Long duplicateCategory(Long id) throws EntityNotFoundException {
		try {
			return services.duplicateCategory(id);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public void updateCategory(Category category)
			throws EntityNotFoundException {
		try {
			services.updateCategory(category);
		} catch (BusinessException e) {
			Log.warn(e);
		}
	}

	@Override
	public void deleteCategory(Long id) throws EntityNotFoundException {
		try {
			services.deleteCategory(id);
		} catch (BusinessException e) {
			Log.warn(e);
		}
	}

	@Override
	public Category findCategoryById(Long id) throws EntityNotFoundException {
		try {
			return services.findCategoryById(id);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

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
	public void deleteTask(Long id) throws EntityNotFoundException {
		try {
			services.deleteTask(id);
		} catch (BusinessException e) {
			Log.warn(e);
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
	public void updateTask(Task task) throws EntityNotFoundException {
		try {
			services.updateTask(task);
		} catch (BusinessException e) {
			Log.warn(e);
		}
	}

	@Override
	public Task findTaskById(Long id) throws EntityNotFoundException {
		try {
			return services.findTaskById(id);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public List<Task> findInboxTasksByUserId(Long id)
			throws EntityNotFoundException {
		try {
			return services.findInboxTasksByUserId(id);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public List<Task> findWeekTasksByUserId(Long id)
			throws EntityNotFoundException {
		try {
			return services.findWeekTasksByUserId(id);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public List<Task> findTodayTasksByUserId(Long id)
			throws EntityNotFoundException {
		try {
			return services.findTodayTasksByUserId(id);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
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

	@Override
	public List<Task> findFinishedTasksByCategoryId(Long catId)
			throws EntityNotFoundException {
		try {
			return services.findFinishedTasksByCategoryId(catId);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public List<Task> findFinishedInboxTasksByUserId(Long userId)
			throws EntityNotFoundException {
		try {
			return services.findFinishedInboxTasksByUserId(userId);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public List<Task> findUnfinishedTasksByUserId(Long id)
			throws EntityNotFoundException {
		try {
			return services.findUnfinishedTasksByUserId(id);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

	@Override
	public List<Task> findTasksByUserId(Long id) throws EntityNotFoundException {
		try {
			return services.findTasksByUserId(id);
		} catch (BusinessException e) {
			Log.warn(e);
			return null;
		}
	}

}
