package com.cognizant.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;





@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({FavoritesEmptyException.class})
	public ResponseEntity<CustomErrorResponse> handleItemNotFoundException(FavoritesEmptyException ex){
		CustomErrorResponse response=new CustomErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setReason("No movie is added to favorites");
		response.setMessage(ex.getMessage());
		
		return new ResponseEntity<CustomErrorResponse>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler({Exception.class})
	public ResponseEntity<CustomErrorResponse> handleException(Exception ex){
		CustomErrorResponse response=new CustomErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setReason("Inavalid information");
		response.setMessage(ex.getMessage());
		
		return new ResponseEntity<CustomErrorResponse>(response,HttpStatus.NOT_FOUND);
	}

}
