package ru.elreni.spring.SensorRestService.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.elreni.spring.SensorRestService.models.Measurement;
import ru.elreni.spring.SensorRestService.repositories.MeasurementRepository;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
	
	MeasurementRepository measurementRepository;
	SensorsService sensorsService;

	@Autowired
	public MeasurementsService(MeasurementRepository measurementRepository, SensorsService sensorsService) {
		super();
		this.measurementRepository = measurementRepository;
		this.sensorsService = sensorsService;
	}

	public Measurement findOne(int id) {
		return measurementRepository.findById(id).get();
	}
	
	public List<Measurement> findAll() {
		return measurementRepository.findAll();
	}
	
	
	public Long countRainingDays(boolean raining) {
		return measurementRepository.countByRaining(raining);
	}
	
	
	@Transactional
	public void add (Measurement measurement) {		
		enrichMeasurement(measurement);
		measurementRepository.save(measurement);
	}
	
	private Measurement enrichMeasurement(Measurement measurement) {
		measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()).get());
		measurement.setMeasured_at(new Date());
		return measurement;
	}
}
