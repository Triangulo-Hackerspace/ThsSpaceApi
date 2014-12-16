package eu.kielczewski.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.kielczewski.example.domain.State;

public interface StateRepository extends JpaRepository<State, Long> {
}
