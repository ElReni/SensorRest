package ru.elreni.spring.SensorRestService.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import ru.elreni.spring.SensorRestService.dto.SensorDTO;
import ru.elreni.spring.SensorRestService.models.Sensor;
import ru.elreni.spring.SensorRestService.services.SensorsService;
import ru.elreni.spring.SensorRestService.util.ErrorBuilder;
import ru.elreni.spring.SensorRestService.util.ErrorResponse;
import ru.elreni.spring.SensorRestService.util.SensorServiceException;
import ru.elreni.spring.SensorRestService.util.SensorValidator;

@Controller
@RequestMapping("/sensors")
public class SensorsController {
	
	SensorsService sensorsService;
	SensorValidator sensorValidator;
	ModelMapper modelMapper;
	
	@Autowired
	public SensorsController(SensorsService sensorsService, SensorValidator sensorValidator, ModelMapper modelMapper) {
		super();
		this.sensorsService = sensorsService;
		this.sensorValidator = sensorValidator;
		this.modelMapper = modelMapper;
	}

	@PostMapping("/registration")
	@ResponseBody
	public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
		
		Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
		
		sensorValidator.validate(sensor, bindingResult);
		
		if (bindingResult.hasErrors()) {
			ErrorBuilder.createSensorServiceException(bindingResult);
		}		
		
		sensorsService.save(sensor);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(SensorServiceException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	
}
