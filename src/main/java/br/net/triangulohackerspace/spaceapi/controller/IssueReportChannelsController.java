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

import br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels;
import br.net.triangulohackerspace.spaceapi.service.Services;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.factory.SpaceServiceFactory;

@RestController
public class IssueReportChannelsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssueReportChannelsController.class);
    
    @Autowired
	private SpaceServiceFactory<IssueReportChannels, Long> spaceServiceFactory;
    
    @RequestMapping(value = "/issueReportChannels", method = RequestMethod.POST)
    public IssueReportChannels createIssueReportChannels(@RequestBody @Valid final IssueReportChannels issueReportChannels) {
        LOGGER.debug("Received request to create the {}", issueReportChannels);
        return spaceServiceFactory.getService(Services.IssueReportChannels).save(issueReportChannels);
    }

    @RequestMapping(value = "/issueReportChannels", method = RequestMethod.GET)
    public List<IssueReportChannels> listIssueReportChannelss() {
		LOGGER.debug("Received request to list all issueReportChannelss");
        return spaceServiceFactory.getService(Services.IssueReportChannels).getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleIssueReportChannelsAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
