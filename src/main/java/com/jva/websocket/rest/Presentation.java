package com.jva.websocket.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("presentation")
public class Presentation {

	public static Integer page = 1;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Integer getPage() {
		return page;
	}

}
