package com.casestudy.bookService.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionRestControllerAdvice {

	@ExceptionHandler(ApplicationException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionResponseMessage handleResourceNotFoundException(RuntimeException ex) {
		return sendResponse(HttpStatus.NOT_FOUND, ex);
	}

	private ExceptionResponseMessage sendResponse(HttpStatus status, RuntimeException ex) {

		return new ExceptionResponseMessage(Instant.now(), status.value(),
				ex.getClass().toString(), ex.getMessage());
	}
}
