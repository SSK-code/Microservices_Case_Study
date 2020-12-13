package com.casestudy.subscriptionService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 4439613667504619144L;
	
	public ApplicationException() {
		this("Subscription service is unable to find resource!");
	}

	public ApplicationException(String message) {
		this(message, null);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

}
