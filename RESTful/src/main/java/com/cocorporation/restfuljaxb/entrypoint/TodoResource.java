package com.cocorporation.restfuljaxb.entrypoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/todo")
public class TodoResource {
	// This method is called if XMLis request
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Todo getXML() {
		Todo todo = new Todo();
		todo.setSummary("This is my first todo");
		todo.setDescription("This is my first todo");
		//todo.setNodeRandom("And I added this myself in the getXML");
		return todo;
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	public void test(){
		System.out.println("test");
	}

	// This can be used to test the integration with the browser
	@GET
	@Produces({ MediaType.TEXT_XML })
	public Todo getHTML() {
		Todo todo = new Todo();
		todo.setSummary("This is my first todo");
		todo.setDescription("This is my first todo");
		//todo.setNodeRandom("And I added this myself in the getHTML");
		return todo;
	}

}