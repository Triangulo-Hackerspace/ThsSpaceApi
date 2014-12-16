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

import eu.kielczewski.example.domain.Space;
import eu.kielczewski.example.service.BusinessService;
import eu.kielczewski.example.service.exception.AlreadyExistsException;

@RestController
public class SpaceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceController.class);
   
    @Inject
    private BusinessService<Space> businessService;

    @RequestMapping(value = "/space", method = RequestMethod.POST)
    public Space createSpace(@RequestBody @Valid final Space space) {
        LOGGER.debug("Received request to create the {}", space);
        return businessService.save(space);
    }

    @RequestMapping(value = "/space", method = RequestMethod.GET)
    public List<Space> listSpaces() {
		LOGGER.debug("Received request to list all spaces");
        return businessService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleSpaceAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
