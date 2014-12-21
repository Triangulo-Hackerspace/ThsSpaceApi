package br.net.triangulohackerspace.spaceapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.net.triangulohackerspace.spaceapi.domain.State;
import br.net.triangulohackerspace.spaceapi.repository.StateRepository;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.impl.StateServiceImpl;
import br.net.triangulohackerspace.spaceapi.util.StateUtil;

@RunWith(MockitoJUnitRunner.class)
public class StateServiceImplTest {

	@Mock
	private StateRepository stateRepository;

	private StateService stateService;

	@Before
	public void setUp() throws Exception {
		stateService = new StateServiceImpl(stateRepository);
	}

	@Test
	public void shouldSaveNewState_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedStateShouldBeReturned()
			throws Exception {
		final State savedState = stubRepositoryToReturnStateOnSave();
		final State state = StateUtil.createState();
		final State returnedState = stateService.save(state);
		// verify repository was called with state
		verify(stateRepository, times(1)).save(state);
		assertEquals("Returned state should come from the repository",
				savedState, returnedState);
	}

	@Test
	public void shouldSaveNewState_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown()
			throws Exception {
		stubRepositoryToReturnExistingState();
		try {
			final State state = StateUtil.createState();
			;
			stateService.save(state);
			fail("Expected exception");
		} catch (AlreadyExistsException ignored) {
		}
		verify(stateRepository, never()).save(any(State.class));
	}

	@Test
	public void shouldListAllStates_GivenThereExistSome_ThenTheCollectionShouldBeReturned()
			throws Exception {
		stubRepositoryToReturnExistingStates(1);
		Collection<State> list = stateService.getList();
		assertNotNull(list);
		assertEquals(1, list.size());
		verify(stateRepository, times(1)).findAll();
	}

	@Test
	public void shouldListAllStates_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned()
			throws Exception {
		stubRepositoryToReturnExistingStates(0);
		Collection<State> list = stateService.getList();
		assertNotNull(list);
		assertTrue(list.isEmpty());
		verify(stateRepository, times(1)).findAll();
	}

	private State stubRepositoryToReturnStateOnSave() {
		final State state = StateUtil.createState();
		when(stateRepository.save(any(State.class))).thenReturn(state);
		return state;
	}

	private void stubRepositoryToReturnExistingState() {
		final State state = StateUtil.createState();
		when(stateRepository.findOne(state.getId())).thenReturn(state);
	}

	private void stubRepositoryToReturnExistingStates(int howMany) {

		when(stateRepository.findAll()).thenReturn(StateUtil.createStateList(howMany));
	}
}
