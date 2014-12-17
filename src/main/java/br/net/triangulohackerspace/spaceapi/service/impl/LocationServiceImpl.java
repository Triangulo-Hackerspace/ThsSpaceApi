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

import br.net.triangulohackerspace.spaceapi.domain.Location;
import br.net.triangulohackerspace.spaceapi.repository.LocationRepository;
import br.net.triangulohackerspace.spaceapi.service.LocationService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@Service
@Validated
public class LocationServiceImpl implements LocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);
    private final LocationRepository repository;

    @Inject
    public LocationServiceImpl(final LocationRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Location save(@NotNull @Valid final Location location) {
        LOGGER.debug("Creating {}", location);
        Location existing = repository.findOne(location.getId());
        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a location with id=%s", location.getId()));
        }
        return repository.save(location);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Location> getList() {
		LOGGER.debug("Retrieving the list of all locations");
        return repository.findAll();
    }

}
