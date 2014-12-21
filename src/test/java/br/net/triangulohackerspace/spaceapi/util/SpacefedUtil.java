package br.net.triangulohackerspace.spaceapi.util;

import java.util.ArrayList;
import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.Spacefed;

public class SpacefedUtil {

	private static final Boolean SPACE_NET = false;
	private static final Boolean SPACE_AML = false;
	private static final Boolean SPACE_PHONE = false;
	
	private SpacefedUtil() {
	}

	public static Spacefed createSpacefed() {
		return new Spacefed(false, false, false, SpaceUtil.getSpace());
	}

	public static List<Spacefed> createSpacefedList(int howMany) {
		List<Spacefed> spacefedList = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			spacefedList.add(new Spacefed(false, false, false, SpaceUtil.getSpace()));
		}
		return spacefedList;
	}
	
	public static Spacefed getSpacefed() {
		return new Spacefed(SPACE_NET, SPACE_AML, SPACE_PHONE, SpaceUtil.getSpace());
	}

}
