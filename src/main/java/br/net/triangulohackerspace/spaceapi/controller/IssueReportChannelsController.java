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

import br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels;
import br.net.triangulohackerspace.spaceapi.service.IssueReportChannelsService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@RestController
public class IssueReportChannelsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssueReportChannelsController.class);
    
    @Inject
    private IssueReportChannelsService issueService;
    
    @Inject
	public IssueReportChannelsController(final IssueReportChannelsService issueService) {
		this.issueService = issueService;
	}

    @RequestMapping(value = "/issueReportChannels", method = RequestMethod.POST)
    public IssueReportChannels createIssueReportChannels(@RequestBody @Valid final IssueReportChannels issueReportChannels) {
        LOGGER.debug("Received request to create the {}", issueReportChannels);
        return issueService.save(issueReportChannels);
    }

    @RequestMapping(value = "/issueReportChannels", method = RequestMethod.GET)
    public List<IssueReportChannels> listIssueReportChannelss() {
		LOGGER.debug("Received request to list all issueReportChannelss");
        return issueService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleIssueReportChannelsAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
