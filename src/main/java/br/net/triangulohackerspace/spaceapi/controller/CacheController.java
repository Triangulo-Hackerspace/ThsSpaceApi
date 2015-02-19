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

import br.net.triangulohackerspace.spaceapi.domain.Cache;
import br.net.triangulohackerspace.spaceapi.service.Services;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.factory.SpaceServiceFactory;

@RestController
public class CacheController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CacheController.class);
	
	@Autowired
	private SpaceServiceFactory<Cache, Long> spaceServiceFactory;

	@RequestMapping(value = "/cache", method = RequestMethod.POST)
	public Cache createCache(@RequestBody @Valid final Cache cache) {
		
		LOGGER.debug("Received request to create the {}", cache);
		return spaceServiceFactory.getService(Services.Cache).save(cache);
	}

	@RequestMapping(value = "/cache", method = RequestMethod.GET)
	public List<Cache> listCaches() {
		LOGGER.debug("Received request to list all caches");
		return spaceServiceFactory.getService(Services.Cache).getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleCacheAlreadyExistsException(AlreadyExistsException e) {
		return e.getMessage();
	}

}
