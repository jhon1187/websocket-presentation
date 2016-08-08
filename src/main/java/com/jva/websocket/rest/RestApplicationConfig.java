package com.jva.websocket.rest;

import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;

public class RestApplicationConfig extends ResourceConfig {
	public RestApplicationConfig() {
		packages("com.jva.websocket.rest");
		EncodingFilter.enableFor(this, GZipEncoder.class);
	}
}
