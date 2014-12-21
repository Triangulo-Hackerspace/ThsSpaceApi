package br.net.triangulohackerspace.spaceapi.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.net.triangulohackerspace.spaceapi.domain.Space;
import br.net.triangulohackerspace.spaceapi.domain.State;

public interface StateRepository extends JpaRepository<State, Long> {
	
	@Query(value = "SELECT state FROM br.net.triangulohackerspace.spaceapi.domain.State state WHERE state.space.id = :spaceId")
	State findBySpaces(@Param("spaceId") Long spaceId);
	
	List<State> findBySpace(Space space, Pageable page);
}
