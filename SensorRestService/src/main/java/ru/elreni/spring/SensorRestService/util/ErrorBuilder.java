package ru.elreni.spring.SensorRestService.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorBuilder {
	
	public static void createSensorServiceException(BindingResult bindingResult) {
		StringBuilder exceptionMsg = new StringBuilder();
		List<FieldError> errors = bindingResult.getFieldErrors();
		
		for (FieldError error : errors) {
			exceptionMsg.append(error.getField())
			.append(" - ")
			.append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
			.append("; ");		
		}
		
		throw new SensorServiceException(exceptionMsg.toString());
	}
}
