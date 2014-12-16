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

import eu.kielczewski.example.domain.IssueReportChannels;
import eu.kielczewski.example.service.BusinessService;
import eu.kielczewski.example.service.exception.AlreadyExistsException;

@RestController
public class IssueReportChannelsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssueReportChannelsController.class);
    
    @Inject
    private BusinessService<IssueReportChannels> businessService;

    @RequestMapping(value = "/issueReportChannels", method = RequestMethod.POST)
    public IssueReportChannels createIssueReportChannels(@RequestBody @Valid final IssueReportChannels issueReportChannels) {
        LOGGER.debug("Received request to create the {}", issueReportChannels);
        return businessService.save(issueReportChannels);
    }

    @RequestMapping(value = "/issueReportChannels", method = RequestMethod.GET)
    public List<IssueReportChannels> listIssueReportChannelss() {
		LOGGER.debug("Received request to list all issueReportChannelss");
        return businessService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleIssueReportChannelsAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
