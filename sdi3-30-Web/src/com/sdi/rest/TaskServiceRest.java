package com.sdi.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.dto.Category;
import com.sdi.dto.Task;

public interface TaskServiceRest {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Long createCategory(Category category) throws EntityNotFoundException;
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Long duplicateCategory(Long id) throws EntityNotFoundException;
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void updateCategory(Category category) throws EntityNotFoundException;
	
	@DELETE
	@Path("{id}")
	public void deleteCategory(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Category findCategoryById(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Category> findCategoriesByUserId(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Long createTask(Task task) throws EntityNotFoundException;
	
	@DELETE
	@Path("{id}")
	public void deleteTask(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void markTaskAsFinished(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void updateTask(Task task) throws EntityNotFoundException;
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Task findTaskById(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> findInboxTasksByUserId(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> findWeekTasksByUserId(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> findTodayTasksByUserId(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@GET
	@Path("{catId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> findTasksByCategoryId(@PathParam("catId") Long catId) throws EntityNotFoundException;
	
	@GET
	@Path("{catId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> findFinishedTasksByCategoryId(@PathParam("catId") Long catId) throws EntityNotFoundException;
	
	@GET
	@Path("{userId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> findFinishedInboxTasksByUserId(@PathParam("userId") Long userId) throws EntityNotFoundException;
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> findUnfinishedTasksByUserId(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> findTasksByUserId(@PathParam("id") Long id) throws EntityNotFoundException;
}
