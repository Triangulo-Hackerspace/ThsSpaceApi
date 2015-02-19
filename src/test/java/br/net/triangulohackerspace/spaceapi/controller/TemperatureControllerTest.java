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

import br.net.triangulohackerspace.spaceapi.controller.TemperatureController;
import br.net.triangulohackerspace.spaceapi.domain.Temperature;
import br.net.triangulohackerspace.spaceapi.service.TemperatureService;
import br.net.triangulohackerspace.spaceapi.util.TemperatureUtil;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureControllerTest {

    @Mock
    private TemperatureService temperatureService;

    @Inject
    private TemperatureController temperatureController;

    @Before
    public void setUp() throws Exception {
    	//temperatureController = new TemperatureController(temperatureService);
    }

    @Test
    public void shouldCreateTemperature() throws Exception {
        final Temperature savedTemperature = stubServiceToReturnStoredTemperature();
        final Temperature temperature = TemperatureUtil.createTemperature();
        Temperature returnedTemperature = temperatureController.createTemperature(temperature);
        // verify temperature was passed to TemperatureService
        verify(temperatureService, times(1)).save(temperature);
        assertEquals("Returned temperature should come from the service", savedTemperature, returnedTemperature);
    }

    private Temperature stubServiceToReturnStoredTemperature() {
        final Temperature temperature = TemperatureUtil.createTemperature();
        when(temperatureService.save(any(Temperature.class))).thenReturn(temperature);
        return temperature;
    }


    @Test
    public void shouldListAllTemperatures() throws Exception {
        stubServiceToReturnExistingTemperatures(10);
        Collection<Temperature> temperatures = temperatureController.listTemperatures();
        assertNotNull(temperatures);
        assertEquals(10, temperatures.size());
        // verify temperature was passed to TemperatureService
        verify(temperatureService, times(1)).getList();
    }

    private void stubServiceToReturnExistingTemperatures(int howMany) {
        when(temperatureService.getList()).thenReturn(TemperatureUtil.createTemperatureList(howMany));
    }

}
