package eu.kielczewski.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.kielczewski.example.domain.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
