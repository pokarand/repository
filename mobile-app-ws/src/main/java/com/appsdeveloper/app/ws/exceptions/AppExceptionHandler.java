package com.appsdeveloper.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.appsdeveloper.app.ws.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleAnyException(Exception ex,WebRequest request) {
		
		String errorMsg = ex.getLocalizedMessage();
		
		if(errorMsg == null) errorMsg = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(),errorMsg);
		
		return new ResponseEntity<Object>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	//Handling multiple exceptions
	@ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
	public ResponseEntity<Object> handleNullPointerException(Exception ex,WebRequest request) {
		
		String errorMsg = ex.getLocalizedMessage();
		
		if(errorMsg == null) errorMsg = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(),errorMsg);
		
		return new ResponseEntity<Object>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

}
