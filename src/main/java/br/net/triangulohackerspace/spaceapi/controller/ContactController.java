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

import br.net.triangulohackerspace.spaceapi.domain.Contact;
import br.net.triangulohackerspace.spaceapi.service.Services;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.factory.SpaceServiceFactory;

@RestController
public class ContactController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContactController.class);

	@Autowired
	private SpaceServiceFactory<Contact, Long> spaceServiceFactory;

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public Contact createContact(@RequestBody @Valid final Contact contact) {
		LOGGER.debug("Received request to create the {}", contact);
		return spaceServiceFactory.getService(Services.Contact).save(contact);
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public List<Contact> listContacts() {
		LOGGER.debug("Received request to list all contacts");
		return spaceServiceFactory.getService(Services.Contact).getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleContactAlreadyExistsException(AlreadyExistsException e) {
		return e.getMessage();
	}

}
