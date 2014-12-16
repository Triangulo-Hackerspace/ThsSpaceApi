package eu.kielczewski.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.kielczewski.example.domain.Cache;

public interface CacheRepository extends JpaRepository<Cache, Long> {
}
