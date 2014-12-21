package br.net.triangulohackerspace.spaceapi.controller;

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

import br.net.triangulohackerspace.spaceapi.domain.State;
import br.net.triangulohackerspace.spaceapi.domain.to.StateTO;
import br.net.triangulohackerspace.spaceapi.service.StateService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@RestController
public class StateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateController.class);
    
    @Inject
    private StateService stateService;
    
    @Inject
	public StateController(final StateService stateService) {
		this.stateService = stateService;
	}

    @RequestMapping(value = "/state/{userId}/{entry}", method = RequestMethod.POST)
    public State createStateByUser(@RequestBody @Valid final State state, @PathVariable("userId") Long userId, @PathVariable("entry") String entry) {
        LOGGER.debug("Received request to create the {}", state);
        return stateService.saveByUser(state, userId, entry);
    }

    @RequestMapping(value = "/state", method = RequestMethod.POST)
    public State createState(@RequestBody @Valid final State state) {
        LOGGER.debug("Received request to create the {}", state);
        return stateService.save(state);
    }

    
    @RequestMapping(value = "/states", method = RequestMethod.GET)
    public List<StateTO> listStatesBySpace() {
		LOGGER.debug("Received request to list all states");
        return stateService.getStateList();
    }
    
    @RequestMapping(value = "/state", method = RequestMethod.GET)
    public List<State> listStates() {
		LOGGER.debug("Received request to list all states");
        return stateService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleStateAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
