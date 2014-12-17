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

import br.net.triangulohackerspace.spaceapi.domain.Spacefed;
import br.net.triangulohackerspace.spaceapi.repository.SpacefedRepository;
import br.net.triangulohackerspace.spaceapi.service.SpacefedService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@Service
@Validated
public class SpacefedServiceImpl implements SpacefedService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpacefedServiceImpl.class);
    private final SpacefedRepository repository;

    @Inject
    public SpacefedServiceImpl(final SpacefedRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Spacefed save(@NotNull @Valid final Spacefed spacefed) {
        LOGGER.debug("Creating {}", spacefed);
        Spacefed existing = repository.findOne(spacefed.getId());
        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a spacefed with id=%s", spacefed.getId()));
        }
        return repository.save(spacefed);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Spacefed> getList() {
		LOGGER.debug("Retrieving the list of all spacefeds");
        return repository.findAll();
    }

}
