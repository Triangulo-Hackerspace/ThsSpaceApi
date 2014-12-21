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

import br.net.triangulohackerspace.spaceapi.domain.Spacefed;
import br.net.triangulohackerspace.spaceapi.service.SpacefedService;
import br.net.triangulohackerspace.spaceapi.util.SpacefedUtil;

@RunWith(MockitoJUnitRunner.class)
public class SpacefedControllerTest {

    @Mock
    private SpacefedService spacefedService;

    @Inject
    private SpacefedController spacefedController;

    @Before
    public void setUp() throws Exception {
    	spacefedController = new SpacefedController(spacefedService);
    }

    @Test
    public void shouldCreateSpacefed() throws Exception {
        final Spacefed savedSpacefed = stubServiceToReturnStoredSpacefed();
        final Spacefed spacefed = SpacefedUtil.createSpacefed();
        Spacefed returnedSpacefed = spacefedController.createSpacefed(spacefed);
        // verify spacefed was passed to SpacefedService
        verify(spacefedService, times(1)).save(spacefed);
        assertEquals("Returned spacefed should come from the service", savedSpacefed, returnedSpacefed);
    }

    private Spacefed stubServiceToReturnStoredSpacefed() {
        final Spacefed spacefed = SpacefedUtil.createSpacefed();
        when(spacefedService.save(any(Spacefed.class))).thenReturn(spacefed);
        return spacefed;
    }


    @Test
    public void shouldListAllSpacefeds() throws Exception {
        stubServiceToReturnExistingSpacefeds(10);
        Collection<Spacefed> spacefeds = spacefedController.listSpacefeds();
        assertNotNull(spacefeds);
        assertEquals(10, spacefeds.size());
        // verify spacefed was passed to SpacefedService
        verify(spacefedService, times(1)).getList();
    }

    private void stubServiceToReturnExistingSpacefeds(int howMany) {
        when(spacefedService.getList()).thenReturn(SpacefedUtil.createSpacefedList(howMany));
    }

}
