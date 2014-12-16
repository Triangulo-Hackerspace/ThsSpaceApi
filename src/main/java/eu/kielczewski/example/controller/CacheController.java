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

import eu.kielczewski.example.domain.Cache;
import eu.kielczewski.example.service.BusinessService;
import eu.kielczewski.example.service.exception.AlreadyExistsException;

@RestController
public class CacheController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);

    @Inject
    private BusinessService<Cache> businessService;

    @RequestMapping(value = "/cache", method = RequestMethod.POST)
    public Cache createCache(@RequestBody @Valid final Cache cache) {
        LOGGER.debug("Received request to create the {}", cache);
        return businessService.save(cache);
    }

    @RequestMapping(value = "/cache", method = RequestMethod.GET)
    public List<Cache> listCaches() {
		LOGGER.debug("Received request to list all caches");
        return businessService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleCacheAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
