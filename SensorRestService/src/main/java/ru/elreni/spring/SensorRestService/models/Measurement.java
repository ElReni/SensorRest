package ru.elreni.spring.SensorRestService.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "measurement")
public class Measurement {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "temperature")
	@NotNull(message = "Temperature field should be not null")
	@Min(value = -100, message = "Temperature should be no less than -100")
	@Max(value = 100, message = "Temperature should be no grater than 100")
	private Double temperature;
	
	@Column(name = "raining")
	@NotNull(message = "Raining field should be not null")
	private Boolean raining;	
	
	@Column(name = "measured_at")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = "Measured_at field should be not null")
	private Date measured_at;

	@ManyToOne
	@JoinColumn(name = "sensor_name", referencedColumnName = "name")
	@NotNull
	private Sensor sensor;
	
	public Measurement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Measurement(Double temperature,	Boolean raining, Date measured_at) {
		super();
		this.temperature = temperature;
		this.raining = raining;
		this.measured_at = measured_at;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Boolean getRaining() {
		return raining;
	}

	public void setRaining(Boolean raining) {
		this.raining = raining;
	}

	public Date getMeasured_at() {
		return measured_at;
	}

	public void setMeasured_at(Date measured_at) {
		this.measured_at = measured_at;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

		


	
	
}
