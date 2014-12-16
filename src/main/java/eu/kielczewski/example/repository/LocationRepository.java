package eu.kielczewski.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.kielczewski.example.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
