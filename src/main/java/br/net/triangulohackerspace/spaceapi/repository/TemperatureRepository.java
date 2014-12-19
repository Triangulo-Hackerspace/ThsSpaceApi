package br.net.triangulohackerspace.spaceapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.net.triangulohackerspace.spaceapi.domain.Temperature;

public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
	
	@Query(value = "SELECT temperature FROM br.net.triangulohackerspace.spaceapi.domain.Temperature temperature WHERE temperature.sensor.id = :sensorId")
	List<Temperature> findBySensors(@Param("sensorId") Long sensorId);
}
