package br.net.triangulohackerspace.spaceapi.util;

import java.util.ArrayList;
import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.Location;

public class LocationUtil {

	private static final String ADDRESS = "see the documentation";
	private static final Double LAT = 39.240431;
	private static final Double LOG = 5.973817;
	
	private LocationUtil() {
	}

	public static Location createLocation() {
		return getLocation();
	}

	public static List<Location> createLocationList(int howMany) {
		List<Location> locationList = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			locationList.add(getLocation());
		}
		return locationList;
	}
	
	public static Location getLocation() {
		return new Location(ADDRESS, LAT,
				LOG, SpaceUtil.getSpace());
	}
}
