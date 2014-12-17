package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.triangulohackerspace.spaceapi.domain.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
