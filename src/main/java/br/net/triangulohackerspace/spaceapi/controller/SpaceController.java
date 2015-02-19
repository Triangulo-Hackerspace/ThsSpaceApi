package br.net.triangulohackerspace.spaceapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.net.triangulohackerspace.spaceapi.domain.Space;
import br.net.triangulohackerspace.spaceapi.domain.to.SpaceApiTO;
import br.net.triangulohackerspace.spaceapi.service.Services;
import br.net.triangulohackerspace.spaceapi.service.SpaceService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.factory.SpaceServiceFactory;

@RestController
public class SpaceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceController.class);
   
    @Autowired
	private SpaceServiceFactory<Space, Long> spaceServiceFactory;
    
    @Autowired
   	private SpaceService spaceService;

    @RequestMapping(value = "/space", method = RequestMethod.POST)
    public Space createSpace(@RequestBody @Valid final Space space) {
        LOGGER.debug("Received request to create the {}", space);
        return spaceServiceFactory.getService(Services.Space).save(space);
    }

    @RequestMapping(value = "/space", method = RequestMethod.GET)
    public List<Space> listSpaces() {
		LOGGER.debug("Received request to list all spaces");
        return spaceServiceFactory.getService(Services.Space).getList();
    }

    @RequestMapping(value = "/space/api/{spaceId}", method = RequestMethod.GET)
    public SpaceApiTO spaces(@PathVariable("spaceId") Long spaceId) {
		LOGGER.debug("Received request to list all spaces");
        return spaceService.findSpace(spaceId);
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleSpaceAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
