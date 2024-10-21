package ru.elreni.spring.SensorRestService.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.elreni.spring.SensorRestService.models.Measurement;
import ru.elreni.spring.SensorRestService.services.SensorsService;

@Component
public class MeasurementValidator implements Validator {
	
	SensorsService sensorsService;
	
	@Autowired
	public MeasurementValidator(SensorsService sensorsService) {
		super();
		this.sensorsService = sensorsService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Measurement.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Measurement measurement = (Measurement) target;
		
		if (measurement.getSensor() == null) {
            return;
        }
		
		if (sensorsService.findByName(measurement.getSensor().getName()).isEmpty()) {
			errors.rejectValue("sensor", "Receiced measurement is not from registered sensor");
		}

	}

}
