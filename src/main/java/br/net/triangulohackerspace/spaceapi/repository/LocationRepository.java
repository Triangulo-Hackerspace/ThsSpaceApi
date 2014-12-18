package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.net.triangulohackerspace.spaceapi.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	
	@Query(value = "SELECT location FROM br.net.triangulohackerspace.spaceapi.domain.Location location WHERE location.space.id = :spaceId")
	Location findBySpaces(@Param("spaceId") Long spaceId);
}
