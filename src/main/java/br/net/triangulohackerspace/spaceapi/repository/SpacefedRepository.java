package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.net.triangulohackerspace.spaceapi.domain.Spacefed;

public interface SpacefedRepository extends JpaRepository<Spacefed, Long> {
	
	@Query(value = "SELECT spacefed FROM br.net.triangulohackerspace.spaceapi.domain.Spacefed spacefed WHERE spacefed.space.id = :spaceId")
	Spacefed findBySpaces(@Param("spaceId") Long spaceId);
}
