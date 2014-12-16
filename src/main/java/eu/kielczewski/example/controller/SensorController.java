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

import eu.kielczewski.example.domain.Sensor;
import eu.kielczewski.example.service.BusinessService;
import eu.kielczewski.example.service.exception.AlreadyExistsException;

@RestController
public class SensorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorController.class);
    
    @Inject
    private BusinessService<Sensor> businessService;

    @RequestMapping(value = "/sensor", method = RequestMethod.POST)
    public Sensor createSensor(@RequestBody @Valid final Sensor sensor) {
        LOGGER.debug("Received request to create the {}", sensor);
        return businessService.save(sensor);
    }

    @RequestMapping(value = "/sensor", method = RequestMethod.GET)
    public List<Sensor> listSensors() {
		LOGGER.debug("Received request to list all sensors");
        return businessService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleSensorAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}