package br.net.triangulohackerspace.spaceapi.service;

import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.State;
import br.net.triangulohackerspace.spaceapi.domain.to.StateTO;

public interface StateService extends BusinessService<State, Long> {
	
	State saveByUser(State state, Long userId, String entry);
	
	List<StateTO> getStateList();
}
