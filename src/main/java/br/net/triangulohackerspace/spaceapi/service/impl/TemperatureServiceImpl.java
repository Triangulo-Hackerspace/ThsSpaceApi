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

import br.net.triangulohackerspace.spaceapi.domain.Temperature;
import br.net.triangulohackerspace.spaceapi.repository.TemperatureRepository;
import br.net.triangulohackerspace.spaceapi.service.Services;
import br.net.triangulohackerspace.spaceapi.service.TemperatureService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@Service
@Validated
public class TemperatureServiceImpl implements TemperatureService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TemperatureServiceImpl.class);
	private final TemperatureRepository repository;

	@Inject
	public TemperatureServiceImpl(final TemperatureRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Temperature save(@NotNull @Valid final Temperature temperature) {
		LOGGER.debug("Creating {}", temperature);
		Temperature existing = repository.findOne(temperature.getId());
		if (existing != null) {
			throw new AlreadyExistsException(String.format(
					"There already exists a temperature with id=%s",
					temperature.getId()));
		}
		return repository.save(temperature);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Temperature> getList() {
		LOGGER.debug("Retrieving the list of all temperatures");
		return repository.findAll();
	}

	@Override
	public Services appliesTo() {
		return Services.Temperature;
	}

}
