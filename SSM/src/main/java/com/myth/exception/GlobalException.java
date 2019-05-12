package com.myth.exception;

import com.myth.common.Response;
import org.springframework.http.server.ServerHttpResponse;

public class GlobalException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private Response response;
	
	public GlobalException(Response response) {
		super(response.toString());
		this.response = response;
	}

	public Response getResponse() {
		return response;
	}

}