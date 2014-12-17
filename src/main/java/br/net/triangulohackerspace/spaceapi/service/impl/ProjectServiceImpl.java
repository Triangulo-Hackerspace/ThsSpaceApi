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

import br.net.triangulohackerspace.spaceapi.domain.Project;
import br.net.triangulohackerspace.spaceapi.repository.ProjectRepository;
import br.net.triangulohackerspace.spaceapi.service.ProjectService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@Service
@Validated
public class ProjectServiceImpl implements ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final ProjectRepository repository;

    @Inject
    public ProjectServiceImpl(final ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Project save(@NotNull @Valid final Project project) {
        LOGGER.debug("Creating {}", project);
        Project existing = repository.findOne(project.getId());
        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a project with id=%s", project.getId()));
        }
        return repository.save(project);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> getList() {
		LOGGER.debug("Retrieving the list of all projects");
        return repository.findAll();
    }

}
