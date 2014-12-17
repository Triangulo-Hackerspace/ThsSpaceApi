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

import br.net.triangulohackerspace.spaceapi.controller.SpaceController;
import br.net.triangulohackerspace.spaceapi.domain.Space;
import br.net.triangulohackerspace.spaceapi.repository.SpaceRepository;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.impl.SpaceServiceImpl;
import br.net.triangulohackerspace.spaceapi.util.SpaceUtil;

@RunWith(MockitoJUnitRunner.class)
public class SpaceServiceImplTest {

    @Mock
    private SpaceRepository spaceRepository;

    @Inject
    private SpaceController spaceController;

    @Before
    public void setUp() throws Exception {
    	//spaceController = new SpaceServiceImpl(spaceRepository);
    }

    @Test
    public void shouldSaveNewSpace_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedSpaceShouldBeReturned() throws Exception {
        final Space savedSpace = stubRepositoryToReturnSpaceOnSave();
        final Space space = SpaceUtil.createSpace();
        final Space returnedSpace = spaceController.save(space);
        // verify repository was called with space
        verify(spaceRepository, times(1)).save(space);
        assertEquals("Returned space should come from the repository", savedSpace, returnedSpace);
    }

    private Space stubRepositoryToReturnSpaceOnSave() {
        Space space = SpaceUtil.createSpace();
        when(spaceRepository.save(any(Space.class))).thenReturn(space);
        return space;
    }

    @Test
    public void shouldSaveNewSpace_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingSpace();
        try {
        	spaceController.save(SpaceUtil.createSpace());
            fail("Expected exception");
        } catch (AlreadyExistsException ignored) {
        }
        verify(spaceRepository, never()).save(any(Space.class));
    }

    private void stubRepositoryToReturnExistingSpace() {
        final Space space = SpaceUtil.createSpace();
        when(spaceRepository.findOne(space.getId())).thenReturn(space);
    }

    @Test
    public void shouldListAllSpaces_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingSpaces(10);
        Collection<Space> list = spaceController.getList();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(spaceRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingSpaces(int howMany) {
        when(spaceRepository.findAll()).thenReturn(SpaceUtil.createSpaceList(howMany));
    }

    @Test
    public void shouldListAllSpaces_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingSpaces(0);
        Collection<Space> list = spaceController.getList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(spaceRepository, times(1)).findAll();
    }

}
