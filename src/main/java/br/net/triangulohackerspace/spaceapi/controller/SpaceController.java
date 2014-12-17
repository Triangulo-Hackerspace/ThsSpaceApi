package br.net.triangulohackerspace.spaceapi.controller;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.net.triangulohackerspace.spaceapi.domain.Space;
import br.net.triangulohackerspace.spaceapi.service.SpaceService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@RestController
public class SpaceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceController.class);
   
    @Inject
    private SpaceService spaceService;

    @RequestMapping(value = "/space", method = RequestMethod.POST)
    public Space createSpace(@RequestBody @Valid final Space space) {
        LOGGER.debug("Received request to create the {}", space);
        return spaceService.save(space);
    }

    @RequestMapping(value = "/space", method = RequestMethod.GET)
    public List<Space> listSpaces() {
		LOGGER.debug("Received request to list all spaces");
        return spaceService.getList();
    }

    @RequestMapping(value = "/space/api/{spaceId}", method = RequestMethod.GET)
    public Space spaces(@PathVariable("spaceId") Long spaceId) {
		LOGGER.debug("Received request to list all spaces");
        return spaceService.findSpace(spaceId);
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleSpaceAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

	public Collection<Space> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	public Space save(Space space) {
		// TODO Auto-generated method stub
		return null;
	}

}
