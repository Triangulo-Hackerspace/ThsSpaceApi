package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.net.triangulohackerspace.spaceapi.domain.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
	
	@Query(value = "SELECT sensor FROM br.net.triangulohackerspace.spaceapi.domain.Sensor sensor WHERE sensor.space.id = :spaceId")
	Sensor findBySpaces(@Param("spaceId") Long spaceId);
}
