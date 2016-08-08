package com.jva.websocket.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jva.websocket.socket.WebSocketEchoSocket;

@Path("presentation")
public class Presentation {
	
	public static Integer page = 1;
	public static Boolean adminExist = false;
	
	@GET
	@Path("adminExist")
	@Produces(MediaType.TEXT_PLAIN)
	public Boolean adminExist() {
		return adminExist;
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("changeAdminExist")
	public void changeAdminExist() {
		Presentation.adminExist = true;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Integer getPage() {
		return page;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public void change(Integer page) {
		Presentation.page = page;
		WebSocketEchoSocket.send(page.toString());
	}
}
