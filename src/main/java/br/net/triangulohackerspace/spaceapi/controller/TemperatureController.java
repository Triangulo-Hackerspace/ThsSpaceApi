package br.net.triangulohackerspace.spaceapi.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.net.triangulohackerspace.spaceapi.domain.Temperature;
import br.net.triangulohackerspace.spaceapi.service.TemperatureService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@RestController
public class TemperatureController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureController.class);
    
    @Inject
    private TemperatureService temperatureService;
    
    @Inject
	public TemperatureController(final TemperatureService temperatureService) {
		this.temperatureService = temperatureService;
	}

    @RequestMapping(value = "/temperature", method = RequestMethod.POST)
    public Temperature createTemperature(@RequestBody @Valid final Temperature temperature) {
        LOGGER.debug("Received request to create the {}", temperature);
        return temperatureService.save(temperature);
    }

    @RequestMapping(value = "/temperature", method = RequestMethod.GET)
    public List<Temperature> listTemperatures() {
        LOGGER.debug("Received request to list all temperatures");
        return temperatureService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleTemperatureAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
