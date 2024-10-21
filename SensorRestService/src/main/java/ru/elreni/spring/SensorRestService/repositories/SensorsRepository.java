package ru.elreni.spring.SensorRestService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.elreni.spring.SensorRestService.models.Sensor;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
	Optional<Sensor> findByName(String name);
}
