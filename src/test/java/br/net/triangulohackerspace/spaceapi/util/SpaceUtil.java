package br.net.triangulohackerspace.spaceapi.util;

import java.util.ArrayList;
import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.Space;

public class SpaceUtil {

	private static final String API_VERSION = "0.13";
	private static final String NAME = "The space name";
	private static final String LOGO = "http://your-space.com/logo.png";
	private static final String URL = "http://example.com";

	private SpaceUtil() {
	}

	public static Space createSpace() {
		return getSpace();
	}

	public static List<Space> createSpaceList(int howMany) {
		List<Space> spaceList = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			spaceList.add(getSpace());
		}
		return spaceList;
	}

	public static Space getSpace() {
		return new Space(API_VERSION, NAME, LOGO, URL);
	}
}
