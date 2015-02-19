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

import br.net.triangulohackerspace.spaceapi.domain.Spacefed;
import br.net.triangulohackerspace.spaceapi.service.Services;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.factory.SpaceServiceFactory;

@RestController
public class SpacefedController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpacefedController.class);

	@Autowired
	private SpaceServiceFactory<Spacefed, Long> spaceServiceFactory;

	@RequestMapping(value = "/spacefed", method = RequestMethod.POST)
	public Spacefed createSpacefed(@RequestBody @Valid final Spacefed spacefed) {
		LOGGER.debug("Received request to create the {}", spacefed);
		return spaceServiceFactory.getService(Services.Spacefed).save(spacefed);
	}

	@RequestMapping(value = "/spacefed", method = RequestMethod.GET)
	public List<Spacefed> listSpacefeds() {
		LOGGER.debug("Received request to list all spacefeds");
		return spaceServiceFactory.getService(Services.Spacefed).getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleSpacefedAlreadyExistsException(AlreadyExistsException e) {
		return e.getMessage();
	}

}
