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

import br.net.triangulohackerspace.spaceapi.domain.Location;
import br.net.triangulohackerspace.spaceapi.service.LocationService;
import br.net.triangulohackerspace.spaceapi.util.LocationUtil;

@RunWith(MockitoJUnitRunner.class)
public class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @Inject
    private LocationController locationController;

    @Before
    public void setUp() throws Exception {
    	//locationController = new LocationController(locationService);
    }

    @Test
    public void shouldCreateLocation() throws Exception {
        final Location savedLocation = stubServiceToReturnStoredLocation();
        final Location location = LocationUtil.createLocation();
        Location returnedLocation = locationController.createLocation(location);
        // verify location was passed to LocationService
        verify(locationService, times(1)).save(location);
        assertEquals("Returned location should come from the service", savedLocation, returnedLocation);
    }

    private Location stubServiceToReturnStoredLocation() {
        final Location location = LocationUtil.createLocation();
        when(locationService.save(any(Location.class))).thenReturn(location);
        return location;
    }


    @Test
    public void shouldListAllLocations() throws Exception {
        stubServiceToReturnExistingLocations(10);
        Collection<Location> locations = locationController.listLocations();
        assertNotNull(locations);
        assertEquals(10, locations.size());
        // verify location was passed to LocationService
        verify(locationService, times(1)).getList();
    }

    private void stubServiceToReturnExistingLocations(int howMany) {
        when(locationService.getList()).thenReturn(LocationUtil.createLocationList(howMany));
    }

}
