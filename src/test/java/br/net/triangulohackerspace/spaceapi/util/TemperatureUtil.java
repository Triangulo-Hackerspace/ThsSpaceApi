package br.net.triangulohackerspace.spaceapi.util;

import java.util.ArrayList;
import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.Temperature;

public class TemperatureUtil {

	private static final String VALUE = "-";
	private static final String UNIT = "°C";
	private static final String LOCATION = "Roof";

	private TemperatureUtil() {
	}

	public static Temperature createTemperature() {
		return getTemperature();
	}

	public static List<Temperature> createTemperatureList(int howMany) {
		List<Temperature> temperatureList = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			temperatureList.add(getTemperature());
		}
		return temperatureList;
	}

	public static Temperature getTemperature() {
		return new Temperature(VALUE, UNIT, LOCATION, SpaceUtil.getSpace(), SensorUtil.getSensor());
	}
}
