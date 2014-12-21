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

import br.net.triangulohackerspace.spaceapi.domain.User;
import br.net.triangulohackerspace.spaceapi.service.UserService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
  
    @Inject
    private UserService userService;

    @Inject
	public UserController(final UserService userService) {
		this.userService = userService;
	}
    
	@RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody @Valid final User user) {
        LOGGER.debug("Received request to create the {}", user);
        return userService.save(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> listUsers() {
        LOGGER.debug("Received request to list all users");
        return userService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
