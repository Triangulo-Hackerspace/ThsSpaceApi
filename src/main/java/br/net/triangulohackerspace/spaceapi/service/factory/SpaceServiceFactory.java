package br.net.triangulohackerspace.spaceapi.service.factory;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.net.triangulohackerspace.spaceapi.service.BusinessService;
import br.net.triangulohackerspace.spaceapi.service.CacheService;
import br.net.triangulohackerspace.spaceapi.service.ContactService;
import br.net.triangulohackerspace.spaceapi.service.IssueReportChannelsService;
import br.net.triangulohackerspace.spaceapi.service.LocationService;
import br.net.triangulohackerspace.spaceapi.service.ProjectService;
import br.net.triangulohackerspace.spaceapi.service.SensorService;
import br.net.triangulohackerspace.spaceapi.service.SpaceService;
import br.net.triangulohackerspace.spaceapi.service.SpacefedService;
import br.net.triangulohackerspace.spaceapi.service.StateService;
import br.net.triangulohackerspace.spaceapi.service.TemperatureService;
import br.net.triangulohackerspace.spaceapi.service.UserService;

//@Service
public class SpaceServiceFactory<E, ID extends Serializable> {

/*	@Inject
	private CacheService cacheService;
	@Inject
	private ContactService contactService;
	@Inject
	private IssueReportChannelsService issueReportChannelsService;
	@Inject
	private LocationService locationService;
	@Inject
	private ProjectService projectService;
	@Inject
	private SensorService sensorService;
	@Inject
	private SpacefedService spacefedService;
	@Inject
	private SpaceService spaceService;
	@Inject
	private StateService stateService;
	@Inject
	private TemperatureService temperatureService;
	@Inject
	private UserService userService;

	private Map<String, BusinessService<?, Long>> services;

	@PostConstruct
	private void init() {
		services.put("cache", cacheService);
		services.put("contact", contactService);
		services.put("issueReportChannels", issueReportChannelsService);
		services.put("location", locationService);
		services.put("project", projectService);
		services.put("sensor", sensorService);
		services.put("spacefed", spacefedService);
		services.put("space", spaceService);
		services.put("state", stateService);
		services.put("semperature", temperatureService);
		services.put("user", userService);

	}

	public BusinessService<?, Long> getService(String criteria) {
		return services.get(criteria);
	}*/

}
