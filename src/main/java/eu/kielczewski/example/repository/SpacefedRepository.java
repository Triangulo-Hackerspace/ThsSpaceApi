package eu.kielczewski.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.kielczewski.example.domain.Spacefed;

public interface SpacefedRepository extends JpaRepository<Spacefed, Long> {
}
