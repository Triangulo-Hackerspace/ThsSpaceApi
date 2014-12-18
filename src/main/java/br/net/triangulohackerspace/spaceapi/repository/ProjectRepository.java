package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.net.triangulohackerspace.spaceapi.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	@Query(value = "SELECT project FROM br.net.triangulohackerspace.spaceapi.domain.Project project WHERE project.space.id = :spaceId")
	Project findBySpaces(@Param("spaceId") Long spaceId);
}
