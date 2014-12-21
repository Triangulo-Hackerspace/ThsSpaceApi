package br.net.triangulohackerspace.spaceapi.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.net.triangulohackerspace.spaceapi.domain.Cache;
import br.net.triangulohackerspace.spaceapi.domain.Contact;
import br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels;
import br.net.triangulohackerspace.spaceapi.domain.Location;
import br.net.triangulohackerspace.spaceapi.domain.Project;
import br.net.triangulohackerspace.spaceapi.domain.Sensor;
import br.net.triangulohackerspace.spaceapi.domain.Space;
import br.net.triangulohackerspace.spaceapi.domain.Spacefed;
import br.net.triangulohackerspace.spaceapi.domain.State;
import br.net.triangulohackerspace.spaceapi.domain.Temperature;
import br.net.triangulohackerspace.spaceapi.domain.to.ProjectTO;
import br.net.triangulohackerspace.spaceapi.domain.to.SensorTO;
import br.net.triangulohackerspace.spaceapi.domain.to.SpaceApiTO;
import br.net.triangulohackerspace.spaceapi.domain.to.StateTO;
import br.net.triangulohackerspace.spaceapi.repository.CacheRepository;
import br.net.triangulohackerspace.spaceapi.repository.ContactRepository;
import br.net.triangulohackerspace.spaceapi.repository.IssueReportChannelsRepository;
import br.net.triangulohackerspace.spaceapi.repository.LocationRepository;
import br.net.triangulohackerspace.spaceapi.repository.ProjectRepository;
import br.net.triangulohackerspace.spaceapi.repository.SensorRepository;
import br.net.triangulohackerspace.spaceapi.repository.SpaceRepository;
import br.net.triangulohackerspace.spaceapi.repository.SpacefedRepository;
import br.net.triangulohackerspace.spaceapi.repository.StateRepository;
import br.net.triangulohackerspace.spaceapi.repository.TemperatureRepository;
import br.net.triangulohackerspace.spaceapi.service.SpaceService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@Service
@Validated
public class SpaceServiceImpl implements SpaceService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpaceServiceImpl.class);

	@Inject
	private SpaceRepository repository;

	@Inject
	private LocationRepository locationRepository;

	@Inject
	private SpacefedRepository spacefedRepository;

	@Inject
	private ContactRepository contactRepository;

	@Inject
	private IssueReportChannelsRepository issueReportChannelsRepository;

	@Inject
	private StateRepository stateRepository;

	@Inject
	private ProjectRepository projectRepository;

	@Inject
	private CacheRepository cacheRepository;

	@Inject
	private SensorRepository sensorRepository;

	@Inject
	private TemperatureRepository temperatureRepository;

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
			throw new AlreadyExistsException(String.format(
					"There already exists a space with id=%s", space.getId()));
		}
		return repository.save(space);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Space> getList() {
		LOGGER.debug("Retrieving the list of all spaces");
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public SpaceApiTO findSpace(Long id) {
		Space space = repository.findOne(id);

		SpaceApiTO spaceApiTO = new SpaceApiTO(space);

		Location location = locationRepository.findBySpaces(space.getId());

		Spacefed spacefed = spacefedRepository.findBySpaces(space.getId());

		Contact contact = contactRepository.findBySpaces(space.getId());

		IssueReportChannels issueReportChannels = issueReportChannelsRepository
				.findBySpaces(space.getId());

		// State state = stateRepository.findBySpaces(space.getId());

		List<State> states = stateRepository.findBySpace(space,
				new PageRequest(0, 1, Direction.DESC, "id"));

		Cache cache = cacheRepository.findBySpaces(space.getId());

		Sensor sensor = sensorRepository.findBySpaces(space.getId());

		List<Temperature> temperatures = temperatureRepository
				.findBySensors(sensor.getId());

		spaceApiTO.setApiVersion(space.getApiVersion());
		spaceApiTO.setName(space.getName());
		spaceApiTO.setLogo(space.getLogo());
		spaceApiTO.setUrl(space.getUrl());

		spaceApiTO.setLocation(location);

		spaceApiTO.setSpacefed(spacefed);

		spaceApiTO.setContact(contact);

		spaceApiTO.setIssueReportChannels(issueReportChannels);

		ProjectTO projectTO = new ProjectTO();
		
		List<Project> projects = projectRepository.findAll();
		
		projectTO.setProjects(projects);
		
		spaceApiTO.setProjectTO(projectTO);
		
		spaceApiTO.setCache(cache);

		for (State state : states) {
			StateTO stateTO = new StateTO(state);
			spaceApiTO.setStateTO(stateTO);
		}
		
		SensorTO sensorTO = new SensorTO();

		sensorTO.setSensor(sensor);

		sensorTO.setTemperatures(temperatures);

		spaceApiTO.setSensorTO(sensorTO);

		return spaceApiTO;
	}

	@Override
	public Space findById(Long id) {
		return repository.getOne(id);
	}

}
