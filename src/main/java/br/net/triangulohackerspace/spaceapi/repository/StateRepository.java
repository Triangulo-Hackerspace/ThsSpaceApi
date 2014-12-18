package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.net.triangulohackerspace.spaceapi.domain.State;

public interface StateRepository extends JpaRepository<State, Long> {
	
	@Query(value = "SELECT state FROM br.net.triangulohackerspace.spaceapi.domain.State state WHERE state.space.id = :spaceId")
	State findBySpaces(@Param("spaceId") Long spaceId);
}
