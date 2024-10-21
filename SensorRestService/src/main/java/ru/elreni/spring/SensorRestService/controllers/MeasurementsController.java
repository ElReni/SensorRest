package ru.elreni.spring.SensorRestService.controllers;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import ru.elreni.spring.SensorRestService.dto.MeasurementDTO;
import ru.elreni.spring.SensorRestService.dto.MeasurementsResponse;
import ru.elreni.spring.SensorRestService.models.Measurement;
import ru.elreni.spring.SensorRestService.services.MeasurementsService;
import ru.elreni.spring.SensorRestService.util.ErrorBuilder;
import ru.elreni.spring.SensorRestService.util.ErrorResponse;
import ru.elreni.spring.SensorRestService.util.MeasurementValidator;
import ru.elreni.spring.SensorRestService.util.SensorServiceException;
@Controller
@RequestMapping("/measurements")
public class MeasurementsController {
	
	MeasurementsService measurementsService;
	MeasurementValidator measurementValidator;
	ModelMapper modelMapper;	
	
	@Autowired
	public MeasurementsController(MeasurementsService measurementsService, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
		super();
		this.measurementsService = measurementsService;
		this.measurementValidator = measurementValidator;
		this.modelMapper = modelMapper;
	}


	@GetMapping("")
	@ResponseBody()
	public MeasurementsResponse index () {
		 return new MeasurementsResponse(measurementsService.findAll().stream().map(this::convertToMeasurementDTO)
	                .collect(Collectors.toList()));
	}
	
	@PostMapping("/add") 
	public ResponseEntity<HttpStatus> add (@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
		Measurement measurement = convertToMeasurement(measurementDTO);
		
		measurementValidator.validate(measurement, bindingResult);
		if (bindingResult.hasErrors()) {
			ErrorBuilder.createSensorServiceException(bindingResult);
		}
		
		measurementsService.add(measurement);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@GetMapping("/rainyDaysCount")
	@ResponseBody
	public ResponseEntity<String> getRainyDaysCount() {
		return ResponseEntity.ok("Count of rainy days: " + measurementsService.countRainingDays(true));
	}
	
	@ExceptionHandler
	private ResponseEntity<ErrorResponse> handleException(SensorServiceException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
		return modelMapper.map(measurement, MeasurementDTO.class);
	}
	
	private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
		return modelMapper.map(measurementDTO, Measurement.class);
	}
}
