package br.net.triangulohackerspace.spaceapi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import br.net.triangulohackerspace.spaceapi.controller.SpaceController;
import br.net.triangulohackerspace.spaceapi.domain.Space;
import br.net.triangulohackerspace.spaceapi.service.SpaceService;
import br.net.triangulohackerspace.spaceapi.util.SpaceUtil;

@RunWith(MockitoJUnitRunner.class)
public class SpaceControllerTest {

    @Mock
    private SpaceService spaceService;

    @Inject
    private SpaceController spaceController;

    @Before
    public void setUp() throws Exception {
    	spaceController = new SpaceController(spaceService);
    }

    @Test
    public void shouldCreateSpace() throws Exception {
        final Space savedSpace = stubServiceToReturnStoredSpace();
        final Space space = SpaceUtil.createSpace();
        Space returnedSpace = spaceController.createSpace(space);
        // verify space was passed to SpaceService
        verify(spaceService, times(1)).save(space);
        assertEquals("Returned space should come from the service", savedSpace, returnedSpace);
    }

    private Space stubServiceToReturnStoredSpace() {
        final Space space = SpaceUtil.createSpace();
        when(spaceService.save(any(Space.class))).thenReturn(space);
        return space;
    }


    @Test
    public void shouldListAllSpaces() throws Exception {
        stubServiceToReturnExistingSpaces(10);
        Collection<Space> spaces = spaceController.listSpaces();
        assertNotNull(spaces);
        assertEquals(10, spaces.size());
        // verify space was passed to SpaceService
        verify(spaceService, times(1)).getList();
    }

    private void stubServiceToReturnExistingSpaces(int howMany) {
        when(spaceService.getList()).thenReturn(SpaceUtil.createSpaceList(howMany));
    }

}
