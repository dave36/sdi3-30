package com.sdi.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.dto.Category;
import com.sdi.dto.Task;

@Path("/TaskService")
public interface TaskServiceRest {
	
	@GET
	@Path("/listaCategorias{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Category> findCategoriesByUserId(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@PUT
	@Path("/createTask")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Long createTask(Task task) throws EntityNotFoundException;
	
	@PUT
	@Path("/finishTask{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void markTaskAsFinished(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@GET
	@Path("/findTasks{catId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> findTasksByCategoryId(@PathParam("catId") Long catId) throws EntityNotFoundException;
}
