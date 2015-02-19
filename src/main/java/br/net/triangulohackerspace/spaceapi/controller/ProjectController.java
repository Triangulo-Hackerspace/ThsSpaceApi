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

import br.net.triangulohackerspace.spaceapi.domain.Project;
import br.net.triangulohackerspace.spaceapi.service.Services;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.factory.SpaceServiceFactory;

@RestController
public class ProjectController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProjectController.class);

	@Autowired
	private SpaceServiceFactory<Project, Long> spaceServiceFactory;

	@RequestMapping(value = "/project", method = RequestMethod.POST)
	public Project createProject(@RequestBody @Valid final Project project) {
		LOGGER.debug("Received request to create the {}", project);
		return spaceServiceFactory.getService(Services.Project).save(project);
	}

	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public List<Project> listProjects() {
		LOGGER.debug("Received request to list all projects");
		return spaceServiceFactory.getService(Services.Project).getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleProjectAlreadyExistsException(AlreadyExistsException e) {
		return e.getMessage();
	}

}
