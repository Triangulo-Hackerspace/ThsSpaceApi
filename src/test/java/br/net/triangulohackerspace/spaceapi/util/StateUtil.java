package br.net.triangulohackerspace.spaceapi.util;

import java.util.ArrayList;
import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.State;
import br.net.triangulohackerspace.spaceapi.domain.StateStatus;

public class StateUtil {

	private static final Boolean STATUS = true;
	
	private StateUtil() {
	}

	public static State createState() {
		return getState();
	}

	public static List<State> createStateList(int howMany) {
		List<State> stateList = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			stateList.add(getState());
		}
		return stateList;
	}

	public static State getState() {
		return new State(STATUS, SpaceUtil.getSpace(), UserUtil.getUser(), DateUtil.getNowDate(),
				StateStatus.OPEN.getStateStatus());
	}
}
