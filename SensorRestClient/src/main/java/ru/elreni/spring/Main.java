package ru.elreni.spring;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ru.elreni.spring.SensorRestService.dto.MeasurementDTO;
import ru.elreni.spring.SensorRestService.dto.MeasurementsResponse;
import ru.elreni.spring.SensorRestService.dto.SensorDTO;

public class Main {
	
	public static final Random r = new Random();
	public static final SensorDTO sensor = new SensorDTO("NewSensor");

	public static void main(String[] args) {		
		registerSensor(sensor.getName());		
		for (int i = 0; i < 1000; i++) {
			sendMeasurement(genereteMeasurement(-60, 60, sensor));
		}
		
		List<MeasurementDTO> measurements = getMeasurements();
		
		for (MeasurementDTO measurement : measurements) {
        	System.out.println(measurement);
        }
        
	}
	
	public static void registerSensor(String sensorName) {
		final String url = "http://localhost:8080/sensors";
		
		HashMap<String, Object> jsonSensor = new HashMap<>();
		
		jsonSensor.put("name", sensorName);
		
		makePostRequestWithJSONData(url, jsonSensor);
	}
	
	public static void sendMeasurement(MeasurementDTO measurement) {
        final String url = "http://localhost:8080/measurements/add";
        
        HashMap<String, Object> jsonMeasuarement = new HashMap<>();
        
        jsonMeasuarement.put("temperature", measurement.getTemperature());
        jsonMeasuarement.put("raining", measurement.isRaining());
        jsonMeasuarement.put("sensor", Map.of("name", measurement.getSensor().getName()));
        
        makePostRequestWithJSONData(url, jsonMeasuarement);
	}
	
	private static void makePostRequestWithJSONData(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);

            System.out.println("Sending success");
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	
	public static List<MeasurementDTO> getMeasurements() {
		final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements";        
        MeasurementsResponse measurements = restTemplate.getForObject(url, MeasurementsResponse.class);
        
        if (measurements == null || measurements.getMeasurements() == null) {
        	 return Collections.emptyList();
        }
       
        return measurements.getMeasurements();
	}
	
	public static MeasurementDTO genereteMeasurement(double minTemperature, double maxTemperature, SensorDTO sensor) {
		return new MeasurementDTO(r.nextDouble(minTemperature, maxTemperature), r.nextBoolean(), sensor);	
	}

}
