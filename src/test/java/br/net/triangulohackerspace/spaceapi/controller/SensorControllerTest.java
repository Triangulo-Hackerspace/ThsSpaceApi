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

import br.net.triangulohackerspace.spaceapi.controller.SensorController;
import br.net.triangulohackerspace.spaceapi.domain.Sensor;
import br.net.triangulohackerspace.spaceapi.service.SensorService;
import br.net.triangulohackerspace.spaceapi.util.SensorUtil;

@RunWith(MockitoJUnitRunner.class)
public class SensorControllerTest {

    @Mock
    private SensorService sensorService;

    @Inject
    private SensorController sensorController;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldCreateSensor() throws Exception {
        final Sensor savedSensor = stubServiceToReturnStoredSensor();
        final Sensor sensor = SensorUtil.createSensor();
        Sensor returnedSensor = sensorController.createSensor(sensor);
        // verify sensor was passed to SensorService
        verify(sensorService, times(1)).save(sensor);
        assertEquals("Returned sensor should come from the service", savedSensor, returnedSensor);
    }

    private Sensor stubServiceToReturnStoredSensor() {
        final Sensor sensor = SensorUtil.createSensor();
        when(sensorService.save(any(Sensor.class))).thenReturn(sensor);
        return sensor;
    }


    @Test
    public void shouldListAllSensors() throws Exception {
        stubServiceToReturnExistingSensors(10);
        Collection<Sensor> sensors = sensorController.listSensors();
        assertNotNull(sensors);
        assertEquals(10, sensors.size());
        // verify sensor was passed to SensorService
        verify(sensorService, times(1)).getList();
    }

    private void stubServiceToReturnExistingSensors(int howMany) {
        when(sensorService.getList()).thenReturn(SensorUtil.createSensorList(howMany));
    }

}
