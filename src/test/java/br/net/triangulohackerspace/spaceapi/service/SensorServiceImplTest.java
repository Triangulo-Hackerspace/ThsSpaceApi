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

import br.net.triangulohackerspace.spaceapi.domain.Sensor;
import br.net.triangulohackerspace.spaceapi.repository.SensorRepository;
import br.net.triangulohackerspace.spaceapi.service.SensorService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.impl.SensorServiceImpl;
import br.net.triangulohackerspace.spaceapi.util.SensorUtil;

@RunWith(MockitoJUnitRunner.class)
public class SensorServiceImplTest {

    @Mock
    private SensorRepository sensorRepository;

    private SensorService sensorService;

    @Before
    public void setUp() throws Exception {
    	//sensorService = new SensorServiceImpl(sensorRepository);
    }

    @Test
    public void shouldSaveNewSensor_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedSensorShouldBeReturned() throws Exception {
        final Sensor savedSensor = stubRepositoryToReturnSensorOnSave();
        final Sensor sensor = SensorUtil.createSensor();
        final Sensor returnedSensor = sensorService.save(sensor);
        // verify repository was called with sensor
        verify(sensorRepository, times(1)).save(sensor);
        assertEquals("Returned sensor should come from the repository", savedSensor, returnedSensor);
    }

    private Sensor stubRepositoryToReturnSensorOnSave() {
        Sensor sensor = SensorUtil.createSensor();
        when(sensorRepository.save(any(Sensor.class))).thenReturn(sensor);
        return sensor;
    }

    @Test
    public void shouldSaveNewSensor_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingSensor();
        try {
        	sensorService.save(SensorUtil.createSensor());
            fail("Expected exception");
        } catch (AlreadyExistsException ignored) {
        }
        verify(sensorRepository, never()).save(any(Sensor.class));
    }

    private void stubRepositoryToReturnExistingSensor() {
        final Sensor sensor = SensorUtil.createSensor();
        when(sensorRepository.findOne(sensor.getId())).thenReturn(sensor);
    }

    @Test
    public void shouldListAllSensors_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingSensors(10);
        Collection<Sensor> list = sensorService.getList();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(sensorRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingSensors(int howMany) {
        when(sensorRepository.findAll()).thenReturn(SensorUtil.createSensorList(howMany));
    }

    @Test
    public void shouldListAllSensors_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingSensors(0);
        Collection<Sensor> list = sensorService.getList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(sensorRepository, times(1)).findAll();
    }

}
