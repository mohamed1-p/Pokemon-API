package com.pokemonreview.api.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PokemonNotFoundException.class)
	public ResponseEntity<ErrorObject> handlePokemonNotFound(
			PokemonNotFoundException ex, WebRequest request){
		ErrorObject errorObject = new ErrorObject();
		
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setDetails(request.getDescription(false));
		errorObject.setTimeStamp(new Date());
		
		return new ResponseEntity<ErrorObject>(errorObject,
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<ErrorObject> handleReviewNotFound(
			ReviewNotFoundException ex, WebRequest request){
		ErrorObject errorObject = new ErrorObject();
		
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setDetails(request.getDescription(false));
		errorObject.setTimeStamp(new Date());
		
		return new ResponseEntity<ErrorObject>(errorObject,
				HttpStatus.NOT_FOUND);
	}
}
