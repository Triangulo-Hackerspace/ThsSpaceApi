package eu.kielczewski.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.kielczewski.example.domain.Temperature;

public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
}
