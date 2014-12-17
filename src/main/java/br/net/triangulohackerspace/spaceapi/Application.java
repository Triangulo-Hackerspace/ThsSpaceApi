package br.net.triangulohackerspace.spaceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.net.triangulohackerspace.spaceapi.domain.Temperature;
import br.net.triangulohackerspace.spaceapi.domain.User;
import br.net.triangulohackerspace.spaceapi.repository.TemperatureRepository;
import br.net.triangulohackerspace.spaceapi.repository.UserRepository;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application extends SpringBootServletInitializer {

    public static void main(final String[] args) {
       // SpringApplication.run(Application.class, args);
        
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		UserRepository userRepository = context
				.getBean(UserRepository.class);
		userRepository.save(new User(1l, "rogerio", "sena"));
		
		TemperatureRepository temperatureRepository = context
				.getBean(TemperatureRepository.class);
		temperatureRepository.save(new Temperature(1l, "nos"));
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
