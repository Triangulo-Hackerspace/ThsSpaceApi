package br.net.triangulohackerspace.spaceapi;

import static br.net.triangulohackerspace.spaceapi.util.DateUtil.getAtualDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.net.triangulohackerspace.spaceapi.domain.Cache;
import br.net.triangulohackerspace.spaceapi.domain.Contact;
import br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels;
import br.net.triangulohackerspace.spaceapi.domain.Location;
import br.net.triangulohackerspace.spaceapi.domain.Project;
import br.net.triangulohackerspace.spaceapi.domain.Sensor;
import br.net.triangulohackerspace.spaceapi.domain.Space;
import br.net.triangulohackerspace.spaceapi.domain.Spacefed;
import br.net.triangulohackerspace.spaceapi.domain.State;
import br.net.triangulohackerspace.spaceapi.domain.StateStatus;
import br.net.triangulohackerspace.spaceapi.domain.Temperature;
import br.net.triangulohackerspace.spaceapi.domain.User;
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
import br.net.triangulohackerspace.spaceapi.repository.UserRepository;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		// SpringApplication.run(Application.class, args);

		ApplicationContext context = SpringApplication.run(Application.class,
				args);

		CacheRepository cacheRepository = context
				.getBean(CacheRepository.class);

		ContactRepository contactRepository = context
				.getBean(ContactRepository.class);

		IssueReportChannelsRepository issueReportChannelsRepository = context
				.getBean(IssueReportChannelsRepository.class);

		LocationRepository locationRepository = context
				.getBean(LocationRepository.class);

		ProjectRepository projectRepository = context
				.getBean(ProjectRepository.class);

		SensorRepository sensorRepository = context
				.getBean(SensorRepository.class);

		SpacefedRepository spacefedRepository = context
				.getBean(SpacefedRepository.class);

		SpaceRepository spaceRepository = context
				.getBean(SpaceRepository.class);

		StateRepository stateRepository = context
				.getBean(StateRepository.class);

		TemperatureRepository temperatureRepository = context
				.getBean(TemperatureRepository.class);

		UserRepository userRepository = context.getBean(UserRepository.class);

		Space space = new Space("0.13", "The space name",
				"http://your-space.com/logo.png", "http://example.com");
		spaceRepository.save(space);

		Cache cache = new Cache("m.02", space);
		cacheRepository.save(cache);

		Contact contact = new Contact("e@xample.com", "@example",
				"irc://irc.freenode.net/example", "public@lists.example.com",
				"ZUB4YW1wbGUuY29tCg==", space);
		contactRepository.save(contact);

		IssueReportChannels issueReportChannels = new IssueReportChannels(
				"issue_mail", space);
		issueReportChannelsRepository.save(issueReportChannels);

		Location location = new Location("see the documentation", 39.240431,
				5.973817, space);
		locationRepository.save(location);

		Project project = new Project("example", "http://github.com/example",
				space);
		projectRepository.save(project);
		
		Project project2 = new Project("example2", "http://github.com/example2",
				space);
		projectRepository.save(project2);

		Sensor sensor = new Sensor("t1", space);
		sensorRepository.save(sensor);

		Temperature temperature1 = new Temperature();
		temperature1.setLocation("Roof");
		temperature1.setUnit("°C");
		temperature1.setValue("-");
		temperature1.setSensor(sensor);
		temperatureRepository.save(temperature1);

		Temperature temperature2 = new Temperature();
		temperature2.setLocation("Lab");
		temperature2.setUnit("°De");
		temperature2.setValue("-");
		temperature2.setSensor(sensor);
		temperatureRepository.save(temperature2);

		Spacefed spacefed = new Spacefed(false, false, false, space);
		spacefedRepository.save(spacefed);

		User user = new User("rogerio", "sena");
		userRepository.save(user);
	
	/*	State state1 = new State(true, space, user, getPlusDateByDay(-2),
				StateStatus.CLOSE.getStateStatus());
		stateRepository.save(state1);*/

		State state2 = new State(true, space, user, getAtualDate(),
				StateStatus.OPEN.getStateStatus());
		stateRepository.save(state2);
	}
	
	@Override
	protected final SpringApplicationBuilder configure(
			final SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}
