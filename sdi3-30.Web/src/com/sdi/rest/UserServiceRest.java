package com.sdi.rest;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.dto.User;

@Path("/UserService")
public interface UserServiceRest {

	@GET
	@Path("/login{log}&{passw}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User findLoggableUser(@PathParam("log") String login,
			@PathParam("passw") String password) throws EntityNotFoundException;
}
