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

import eu.kielczewski.example.domain.Sensor;
import eu.kielczewski.example.repository.SensorRepository;
import eu.kielczewski.example.service.BusinessService;
import eu.kielczewski.example.service.exception.AlreadyExistsException;

@Service
@Validated
public class SensorServiceImpl implements BusinessService<Sensor> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorServiceImpl.class);
    private final SensorRepository repository;

    @Inject
    public SensorServiceImpl(final SensorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Sensor save(@NotNull @Valid final Sensor sensor) {
        LOGGER.debug("Creating {}", sensor);
        Sensor existing = repository.findOne(sensor.getId());
        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a sensor with id=%s", sensor.getId()));
        }
        return repository.save(sensor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sensor> getList() {
		LOGGER.debug("Retrieving the list of all sensors");
        return repository.findAll();
    }

}
