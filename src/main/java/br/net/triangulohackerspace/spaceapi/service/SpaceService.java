package br.net.triangulohackerspace.spaceapi.service;

import br.net.triangulohackerspace.spaceapi.domain.Space;
import br.net.triangulohackerspace.spaceapi.domain.to.SpaceApiTO;

public interface SpaceService extends BusinessService<Space, Long> {
	SpaceApiTO findSpace(Long id);
	Space findById(Long id);
}
