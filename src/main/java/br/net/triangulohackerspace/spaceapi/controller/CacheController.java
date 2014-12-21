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

import br.net.triangulohackerspace.spaceapi.domain.Cache;
import br.net.triangulohackerspace.spaceapi.service.CacheService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@RestController
public class CacheController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CacheController.class);

	@Inject
	private CacheService cacheService;

	@Inject
	public CacheController(final CacheService cacheService) {
		this.cacheService = cacheService;
	}
	
	@RequestMapping(value = "/cache", method = RequestMethod.POST)
	public Cache createCache(@RequestBody @Valid final Cache cache) {
		LOGGER.debug("Received request to create the {}", cache);
		return cacheService.save(cache);
	}

	@RequestMapping(value = "/cache", method = RequestMethod.GET)
	public List<Cache> listCaches() {
		LOGGER.debug("Received request to list all caches");
		return cacheService.getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleCacheAlreadyExistsException(AlreadyExistsException e) {
		return e.getMessage();
	}

}
