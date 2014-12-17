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

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.net.triangulohackerspace.spaceapi.domain.State;
import br.net.triangulohackerspace.spaceapi.repository.StateRepository;
import br.net.triangulohackerspace.spaceapi.service.StateService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.util.StateUtil;

@RunWith(MockitoJUnitRunner.class)
public class StateServiceImplTest {

    @Mock
    private StateRepository stateRepository;

    @Inject
    private StateService stateService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldSaveNewState_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedStateShouldBeReturned() throws Exception {
        final State savedState = stubRepositoryToReturnStateOnSave();
        final State state = StateUtil.createState();
        final State returnedState = stateService.save(state);
        // verify repository was called with state
        verify(stateRepository, times(1)).save(state);
        assertEquals("Returned state should come from the repository", savedState, returnedState);
    }

    private State stubRepositoryToReturnStateOnSave() {
        State state = StateUtil.createState();
        when(stateRepository.save(any(State.class))).thenReturn(state);
        return state;
    }

    @Test
    public void shouldSaveNewState_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingState();
        try {
        	stateService.save(StateUtil.createState());
            fail("Expected exception");
        } catch (AlreadyExistsException ignored) {
        }
        verify(stateRepository, never()).save(any(State.class));
    }

    private void stubRepositoryToReturnExistingState() {
        final State state = StateUtil.createState();
        when(stateRepository.findOne(state.getId())).thenReturn(state);
    }

    @Test
    public void shouldListAllStates_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingStates(10);
        Collection<State> list = stateService.getList();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(stateRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingStates(int howMany) {
        when(stateRepository.findAll()).thenReturn(StateUtil.createStateList(howMany));
    }

    @Test
    public void shouldListAllStates_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingStates(0);
        Collection<State> list = stateService.getList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(stateRepository, times(1)).findAll();
    }

}
