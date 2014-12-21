package br.net.triangulohackerspace.spaceapi.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.net.triangulohackerspace.spaceapi.domain.State;
import br.net.triangulohackerspace.spaceapi.service.StateService;
import br.net.triangulohackerspace.spaceapi.util.StateUtil;

@RunWith(MockitoJUnitRunner.class)
public class StateControllerTest {

	@Mock
	private StateService stateService;

	@Inject
	private StateController stateController;

	@Before
	public void setUp() throws Exception {
		stateController = new StateController(stateService);
	}

	@Test
	public void shouldCreateState() throws Exception {
		final State savedState = stubServiceToReturnStoredState();
		final State state = StateUtil.createState();
		State returnedState = stateController.createState(state);
		// verify state was passed to StateService
		verify(stateService, times(1)).save(state);
		assertEquals("Returned state should come from the service", savedState,
				returnedState);
	}

	private State stubServiceToReturnStoredState() {
		final State state = StateUtil.createState();
		when(stateService.save(any(State.class))).thenReturn(state);
		return state;
	}

	@Test
	public void shouldListAllStates() throws Exception {
		stubServiceToReturnExistingStates(10);
		Collection<State> states = stateController.listStates();
		assertNotNull(states);
		assertEquals(10, states.size());
		// verify state was passed to StateService
		verify(stateService, times(1)).getList();
	}

	private void stubServiceToReturnExistingStates(int howMany) {
		when(stateService.getList()).thenReturn(
				StateUtil.createStateList(howMany));
	}

}
