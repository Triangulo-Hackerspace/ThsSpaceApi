package br.net.triangulohackerspace.spaceapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.net.triangulohackerspace.spaceapi.domain.Location;
import br.net.triangulohackerspace.spaceapi.service.Services;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.factory.SpaceServiceFactory;

@RestController
public class LocationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);
    
    @Autowired
	private SpaceServiceFactory<Location, Long> spaceServiceFactory;

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public Location createLocation(@RequestBody @Valid final Location location) {
        LOGGER.debug("Received request to create the {}", location);
        return spaceServiceFactory.getService(Services.Location).save(location);
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public List<Location> listLocations() {
		LOGGER.debug("Received request to list all locations");
        return spaceServiceFactory.getService(Services.Location).getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleLocationAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
