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

import eu.kielczewski.example.domain.Location;
import eu.kielczewski.example.repository.LocationRepository;
import eu.kielczewski.example.service.BusinessService;
import eu.kielczewski.example.service.exception.AlreadyExistsException;

@Service
@Validated
public class LocationServiceImpl implements BusinessService<Location> {

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
