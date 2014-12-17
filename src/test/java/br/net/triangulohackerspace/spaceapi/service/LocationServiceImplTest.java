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

import br.net.triangulohackerspace.spaceapi.domain.Location;
import br.net.triangulohackerspace.spaceapi.repository.LocationRepository;
import br.net.triangulohackerspace.spaceapi.service.LocationService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.impl.LocationServiceImpl;
import br.net.triangulohackerspace.spaceapi.util.LocationUtil;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    private LocationService locationService;

    @Before
    public void setUp() throws Exception {
    	//locationService = new LocationServiceImpl(locationRepository);
    }

    @Test
    public void shouldSaveNewLocation_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedLocationShouldBeReturned() throws Exception {
        final Location savedLocation = stubRepositoryToReturnLocationOnSave();
        final Location location = LocationUtil.createLocation();
        final Location returnedLocation = locationService.save(location);
        // verify repository was called with location
        verify(locationRepository, times(1)).save(location);
        assertEquals("Returned location should come from the repository", savedLocation, returnedLocation);
    }

    private Location stubRepositoryToReturnLocationOnSave() {
        Location location = LocationUtil.createLocation();
        when(locationRepository.save(any(Location.class))).thenReturn(location);
        return location;
    }

    @Test
    public void shouldSaveNewLocation_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingLocation();
        try {
        	locationService.save(LocationUtil.createLocation());
            fail("Expected exception");
        } catch (AlreadyExistsException ignored) {
        }
        verify(locationRepository, never()).save(any(Location.class));
    }

    private void stubRepositoryToReturnExistingLocation() {
        final Location location = LocationUtil.createLocation();
        when(locationRepository.findOne(location.getId())).thenReturn(location);
    }

    @Test
    public void shouldListAllLocations_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingLocations(10);
        Collection<Location> list = locationService.getList();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(locationRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingLocations(int howMany) {
        when(locationRepository.findAll()).thenReturn(LocationUtil.createLocationList(howMany));
    }

    @Test
    public void shouldListAllLocations_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingLocations(0);
        Collection<Location> list = locationService.getList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(locationRepository, times(1)).findAll();
    }

}
