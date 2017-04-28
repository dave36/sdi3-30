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

import com.sdi.dto.User;

@Path("/AdminServiceRs")
public interface AdminServiceRest {
	
	@DELETE
	@Path("{id}")
	public void deepDeleteUser(@PathParam("{id}") Long id) throws EntityNotFoundException;
	
	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void disableUser(@PathParam("{id}") Long id) throws EntityNotFoundException;
	
	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void enableUser(@PathParam("{id}") Long id) throws EntityNotFoundException;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<User> findAllUsers();
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User findUserById(@PathParam("{id}") Long id) throws EntityNotFoundException;
	
	public void dropAndInsert();
}
