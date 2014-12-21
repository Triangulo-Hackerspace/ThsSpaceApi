package br.net.triangulohackerspace.spaceapi.util;

import java.util.ArrayList;
import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.Sensor;

public class SensorUtil {

	private static final String NAME = "t1";
	
	private SensorUtil() {
	}

	public static Sensor createSensor() {
		return getSensor();
	}

	public static List<Sensor> createSensorList(int howMany) {
		List<Sensor> sensorList = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			sensorList.add(getSensor());
		}
		return sensorList;
	}

	public static Sensor getSensor() {
		return new Sensor(NAME, SpaceUtil.getSpace());
	}

}
