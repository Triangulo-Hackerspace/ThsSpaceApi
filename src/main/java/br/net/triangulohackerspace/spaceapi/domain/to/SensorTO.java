package br.net.triangulohackerspace.spaceapi.domain.to;

import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.Sensor;
import br.net.triangulohackerspace.spaceapi.domain.Temperature;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorTO {

	@JsonIgnore
	private Sensor sensor;

	@JsonProperty(value = "temperature")
	List<Temperature> temperatures;

	/**
	 * 
	 */
	public SensorTO() {
		super();
	}

	/**
	 * @param temperatures
	 */
	public SensorTO(Sensor sensor, List<Temperature> temperatures) {
		super();
		this.sensor = sensor;
		if (this.sensor == null) {
			this.sensor = new Sensor();
		}
		this.temperatures = temperatures;
	}

	/**
	 * @return the sensor
	 */
	public Sensor getSensor() {
		return this.sensor;
	}

	/**
	 * @param sensor
	 *            the sensor to set
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	/**
	 * @return the temperatures
	 */
	public List<Temperature> getTemperatures() {
		return temperatures;
	}

	/**
	 * @param temperatures
	 *            the temperatures to set
	 */
	public void setTemperatures(List<Temperature> temperatures) {
		this.temperatures = temperatures;
	}

}
