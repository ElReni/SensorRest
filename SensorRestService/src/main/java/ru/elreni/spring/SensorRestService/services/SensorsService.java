package ru.elreni.spring.SensorRestService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.elreni.spring.SensorRestService.models.Sensor;
import ru.elreni.spring.SensorRestService.repositories.SensorsRepository;

@Service
@Transactional(readOnly = true)
public class SensorsService {
	
	SensorsRepository sensorsRepository;

	@Autowired
	public SensorsService(SensorsRepository sensorsRepository) {
		super();
		this.sensorsRepository = sensorsRepository;
	}
	
	public Sensor findOne(int id) {
		return sensorsRepository.findById(id).get();
	}
	
	public List<Sensor> findAll() {
		return sensorsRepository.findAll();
	}
	
	public Optional<Sensor> findByName(String name) {
		return sensorsRepository.findByName(name);
	}
	
	@Transactional
	public void save(Sensor sensor) {
		sensorsRepository.save(sensor);
	}
}
