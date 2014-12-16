package eu.kielczewski.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.kielczewski.example.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
