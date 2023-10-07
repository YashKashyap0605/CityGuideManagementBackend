package com.cityguide.cityguidemanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cityguide.cityguidemanagement.controller.AttractionsController;
import com.cityguide.cityguidemanagement.model.ErrorResponse;

@ControllerAdvice
public class MyControllerAdvice {

	Logger logger = LoggerFactory.getLogger(AttractionsController.class);

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e, WebRequest wee) {
		ErrorResponse res = new ErrorResponse();
		res.setErrorCode(404);
		res.setErrorMessage(e.getMessage());
		logger.error("User not found in database : " + e.getMessage());
		return new ResponseEntity<ErrorResponse>(res, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e,
			WebRequest wee) {
		ErrorResponse res = new ErrorResponse();
		res.setErrorCode(409);
		res.setErrorMessage(e.getMessage());
		logger.error("User Already Exists in Database : " + e.getMessage());
		return new ResponseEntity<ErrorResponse>(res, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(TypeAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleTypeAlreadyExistsException(TypeAlreadyExistsException e,
			WebRequest wee) {
		ErrorResponse res = new ErrorResponse();
		res.setErrorCode(409);
		res.setErrorMessage(e.getMessage());
		logger.error("Type Already Exists in Database : " + e.getMessage());
		return new ResponseEntity<ErrorResponse>(res, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(AttractionNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAttractionNotFoundException(AttractionNotFoundException e,
			WebRequest wee) {
		ErrorResponse res = new ErrorResponse();
		res.setErrorCode(404);
		res.setErrorMessage(e.getMessage());
		logger.error("Attraction not found in Database : " + e.getMessage());
		return new ResponseEntity<ErrorResponse>(res, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AttractionAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleAttractionAlreadyExistsException(AttractionAlreadyExistsException e,
			WebRequest wee) {
		ErrorResponse res = new ErrorResponse();
		res.setErrorCode(409);
		res.setErrorMessage(e.getMessage());
		logger.error("Attraction Already Exists in Database : " + e.getMessage());
		return new ResponseEntity<ErrorResponse>(res, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(InvalidUsernameOrPasswordException.class)
	public ResponseEntity<ErrorResponse> handleInvalidUsernameOrPasswordException(InvalidUsernameOrPasswordException e,
			WebRequest wee) {
		ErrorResponse res = new ErrorResponse();
		res.setErrorCode(400);
		res.setErrorMessage(e.getMessage());
		logger.error("Invalid User Name or Password  : " + e.getMessage());
		return new ResponseEntity<ErrorResponse>(res, HttpStatus.BAD_REQUEST);
	}

}
