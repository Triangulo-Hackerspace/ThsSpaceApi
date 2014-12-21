package br.net.triangulohackerspace.spaceapi.controller;

import java.util.Collection;
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

import br.net.triangulohackerspace.spaceapi.domain.Spacefed;
import br.net.triangulohackerspace.spaceapi.service.SpacefedService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@RestController
public class SpacefedController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpacefedController.class);
    
    @Inject
    private SpacefedService spacefedService;
    
    @Inject
	public SpacefedController(final SpacefedService spacefedService) {
		this.spacefedService = spacefedService;
	}

    @RequestMapping(value = "/spacefed", method = RequestMethod.POST)
    public Spacefed createSpacefed(@RequestBody @Valid final Spacefed spacefed) {
        LOGGER.debug("Received request to create the {}", spacefed);
        return spacefedService.save(spacefed);
    }

    @RequestMapping(value = "/spacefed", method = RequestMethod.GET)
    public List<Spacefed> listSpacefeds() {
		LOGGER.debug("Received request to list all spacefeds");
        return spacefedService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleSpacefedAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

	public Spacefed save(Spacefed spacefed) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Spacefed> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
