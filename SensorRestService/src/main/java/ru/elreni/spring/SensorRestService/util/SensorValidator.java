package ru.elreni.spring.SensorRestService.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.elreni.spring.SensorRestService.models.Sensor;
import ru.elreni.spring.SensorRestService.services.SensorsService;

@Component
public class SensorValidator implements Validator {

	SensorsService sensorService;	
	
	@Autowired
	public SensorValidator(SensorsService sensorService) {
		super();
		this.sensorService = sensorService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Sensor.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {		
		Sensor sensor = (Sensor) target;
		
		if(sensorService.findByName(sensor.getName()).isPresent()) {
			errors.rejectValue("name", "Sensor with that name alredy exists");
		}
		
		
	}
	
}
