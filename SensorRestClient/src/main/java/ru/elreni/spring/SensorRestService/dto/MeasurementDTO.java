package ru.elreni.spring.SensorRestService.dto;



public class MeasurementDTO {

	private double temperature;

	private boolean raining;	

	private SensorDTO sensor;
	
	
		
	public MeasurementDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MeasurementDTO(double temperature, boolean raining, SensorDTO sensor) {
		super();
		this.temperature = temperature;
		this.raining = raining;
		this.sensor = sensor;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public boolean isRaining() {
		return raining;
	}

	public void setRaining(boolean raining) {
		this.raining = raining;
	}

	public SensorDTO getSensor() {
		return sensor;
	}

	public void setSensor(SensorDTO sensor) {
		this.sensor = sensor;
	}

	@Override
	public String toString() {
		return "MeasurementDTO [temperature=" + temperature + ", raining=" + raining + ", sensor=" + sensor + "]";
	}	
	
}
