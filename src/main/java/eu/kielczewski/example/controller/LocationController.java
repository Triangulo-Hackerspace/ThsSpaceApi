package eu.kielczewski.example.controller;

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

import eu.kielczewski.example.domain.Location;
import eu.kielczewski.example.service.BusinessService;
import eu.kielczewski.example.service.exception.AlreadyExistsException;

@RestController
public class LocationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);
    
    @Inject
    private BusinessService<Location> businessService;

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public Location createLocation(@RequestBody @Valid final Location location) {
        LOGGER.debug("Received request to create the {}", location);
        return businessService.save(location);
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public List<Location> listLocations() {
		LOGGER.debug("Received request to list all locations");
        return businessService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleLocationAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
