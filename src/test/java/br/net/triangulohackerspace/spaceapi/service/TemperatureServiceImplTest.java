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

import br.net.triangulohackerspace.spaceapi.domain.Temperature;
import br.net.triangulohackerspace.spaceapi.repository.TemperatureRepository;
import br.net.triangulohackerspace.spaceapi.service.TemperatureService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.impl.TemperatureServiceImpl;
import br.net.triangulohackerspace.spaceapi.util.TemperatureUtil;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureServiceImplTest {

    @Mock
    private TemperatureRepository temperatureRepository;

    private TemperatureService temperatureService;

    @Before
    public void setUp() throws Exception {
    	temperatureService = new TemperatureServiceImpl(temperatureRepository);
    }

    @Test
    public void shouldSaveNewTemperature_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedTemperatureShouldBeReturned() throws Exception {
        final Temperature savedTemperature = stubRepositoryToReturnTemperatureOnSave();
        final Temperature temperature = TemperatureUtil.createTemperature();
        final Temperature returnedTemperature = temperatureService.save(temperature);
        // verify repository was called with temperature
        verify(temperatureRepository, times(1)).save(temperature);
        assertEquals("Returned temperature should come from the repository", savedTemperature, returnedTemperature);
    }

    private Temperature stubRepositoryToReturnTemperatureOnSave() {
        Temperature temperature = TemperatureUtil.createTemperature();
        when(temperatureRepository.save(any(Temperature.class))).thenReturn(temperature);
        return temperature;
    }

    @Test
    public void shouldSaveNewTemperature_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingTemperature();
        try {
        	temperatureService.save(TemperatureUtil.createTemperature());
            fail("Expected exception");
        } catch (AlreadyExistsException ignored) {
        }
        verify(temperatureRepository, never()).save(any(Temperature.class));
    }

    private void stubRepositoryToReturnExistingTemperature() {
        final Temperature temperature = TemperatureUtil.createTemperature();
        when(temperatureRepository.findOne(temperature.getId())).thenReturn(temperature);
    }

    @Test
    public void shouldListAllTemperatures_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingTemperatures(10);
        Collection<Temperature> list = temperatureService.getList();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(temperatureRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingTemperatures(int howMany) {
        when(temperatureRepository.findAll()).thenReturn(TemperatureUtil.createTemperatureList(howMany));
    }

    @Test
    public void shouldListAllTemperatures_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingTemperatures(0);
		Collection<Temperature> list = temperatureService.getList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(temperatureRepository, times(1)).findAll();
    }

}
