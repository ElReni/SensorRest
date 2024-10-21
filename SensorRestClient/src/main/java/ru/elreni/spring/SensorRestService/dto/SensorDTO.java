package ru.elreni.spring.SensorRestService.dto;

public class SensorDTO {

	private String name;
		
	public SensorDTO(String name) {
		super();
		this.name = name;
	}

	public SensorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SensorDTO [name=" + name + "]";
	}
	
}
