package eu.kielczewski.example.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import eu.kielczewski.example.domain.Space;
import eu.kielczewski.example.repository.SpaceRepository;
import eu.kielczewski.example.service.BusinessService;
import eu.kielczewski.example.service.exception.AlreadyExistsException;

@Service
@Validated
public class SpaceServiceImpl implements BusinessService<Space> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceServiceImpl.class);
    private final SpaceRepository repository;

    @Inject
    public SpaceServiceImpl(final SpaceRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Space save(@NotNull @Valid final Space space) {
        LOGGER.debug("Creating {}", space);
        Space existing = repository.findOne(space.getId());
        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a space with id=%s", space.getId()));
        }
        return repository.save(space);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Space> getList() {
		LOGGER.debug("Retrieving the list of all spaces");
        return repository.findAll();
    }

}
