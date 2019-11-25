package com.bridgelabz.usermanagement.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bridgelabz.usermanagement.dao.UserDao;
import com.bridgelabz.usermanagement.dto.UserDto;
import com.bridgelabz.usermanagement.model.User;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    
    @GET
    @Path("/users")
    @Produces("application/json")
    public List<User> getUsers() {
        UserDao dao = new UserDao();
        List users = dao.getUsers();
        return users;
    }
 
    
    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addEmployee(UserDto userdto){
    
        UserDao dao = new UserDao();
        dao.addEmployee(userdto);
        return Response.ok().build();
    }
    
    @POST
    @Path("/verify/{token}")
    @Produces("application/json")
    @Consumes("application/json")
    public String verify(@PathParam("token") String token) {
    	UserDao dao = new UserDao();
    	String response=dao.verify(token);
    	return response;
    	
    }
    
    
    
}
