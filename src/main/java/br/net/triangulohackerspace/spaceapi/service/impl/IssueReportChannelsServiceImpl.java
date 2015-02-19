package br.net.triangulohackerspace.spaceapi.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels;
import br.net.triangulohackerspace.spaceapi.repository.IssueReportChannelsRepository;
import br.net.triangulohackerspace.spaceapi.service.IssueReportChannelsService;
import br.net.triangulohackerspace.spaceapi.service.Services;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@Service
@Validated
public class IssueReportChannelsServiceImpl implements IssueReportChannelsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssueReportChannelsServiceImpl.class);
    private final IssueReportChannelsRepository repository;

    @Inject
    public IssueReportChannelsServiceImpl(final IssueReportChannelsRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public IssueReportChannels save(@NotNull @Valid final IssueReportChannels issueReportChannels) {
        LOGGER.debug("Creating {}", issueReportChannels);
        IssueReportChannels existing = repository.findOne(issueReportChannels.getId());
        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a issueReportChannels with id=%s", issueReportChannels.getId()));
        }
        return repository.save(issueReportChannels);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueReportChannels> getList() {
		LOGGER.debug("Retrieving the list of all issueReportChannelss");
        return repository.findAll();
    }

	@Override
	public Services appliesTo() {
		return Services.IssueReportChannels;
	}

}
