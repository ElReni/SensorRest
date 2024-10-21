package ru.elreni.spring.SensorRestService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.elreni.spring.SensorRestService.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
	Long countByRaining(boolean raining);
}
