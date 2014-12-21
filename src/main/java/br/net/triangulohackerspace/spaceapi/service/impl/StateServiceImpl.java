package br.net.triangulohackerspace.spaceapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.net.triangulohackerspace.spaceapi.domain.State;
import br.net.triangulohackerspace.spaceapi.domain.StateStatus;
import br.net.triangulohackerspace.spaceapi.domain.User;
import br.net.triangulohackerspace.spaceapi.domain.to.StateTO;
import br.net.triangulohackerspace.spaceapi.repository.StateRepository;
import br.net.triangulohackerspace.spaceapi.repository.UserRepository;
import br.net.triangulohackerspace.spaceapi.service.StateService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@Service
@Validated
public class StateServiceImpl implements StateService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StateServiceImpl.class);

	@Inject
	private StateRepository repository;

	@Inject
	private UserRepository userRepository;

	@Inject
	public StateServiceImpl(StateRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public State saveByUser(@NotNull @Valid State state, Long userId,
			String entry) {

		User user = userRepository.findOne(userId);
		state.setUser(user);

		if (entry.equals("OPEN")) { // [TODO] Melhorar buscar por enum
			state.setStateStatus(StateStatus.OPEN.getStateStatus());
		} else if (entry.equals("CLOSE")) {
			state.setStateStatus(StateStatus.CLOSE.getStateStatus());
		}

		LOGGER.debug("Creating {}", state);
		State existing = repository.findOne(state.getId());
		if (existing != null) {
			throw new AlreadyExistsException(String.format(
					"There already exists a state with id=%s", state.getId()));
		}
		return repository.save(state);
	}

	@Override
	@Transactional(readOnly = false)
	public List<StateTO> getStateList() {
		LOGGER.debug("Retrieving the list of all states");

		List<StateTO> stateResults = new ArrayList<>();
		StateTO stateResult = null;

		List<State> states = repository.findAll();

		for (State state : states) {
			stateResult = new StateTO(state);

			// stateResult.setDate(state.getDate());
			// stateResult.setOpen(state.getOpen());
			// stateResult.setSpace(state.getSpace());
			// stateResult.setStateStatus(state.getStateStatus());
			// stateResult.setUser(state.getUser());

			stateResults.add(stateResult);
		}

		return stateResults;
	}

	@Override
	public State save(@NotNull @Valid State state) {
		LOGGER.debug("Creating {}", state);
		State existing = repository.findOne(state.getId());
		if (existing != null) {
			throw new AlreadyExistsException(String.format(
					"There already exists a state with id=%s", state.getId()));
		}
		return repository.save(state);
	}

	@Override
	@Transactional(readOnly = true)
	public List<State> getList() {
		LOGGER.debug("Retrieving the list of all spaces");
		return repository.findAll();
	}
}
