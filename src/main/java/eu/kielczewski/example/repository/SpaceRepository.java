package eu.kielczewski.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.kielczewski.example.domain.Space;

public interface SpaceRepository extends JpaRepository<Space, Long> {
}
