package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.triangulohackerspace.spaceapi.domain.Temperature;

public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
}
