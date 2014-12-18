package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.net.triangulohackerspace.spaceapi.domain.Cache;

public interface CacheRepository extends JpaRepository<Cache, Long> {
	
	@Query(value = "SELECT cache FROM br.net.triangulohackerspace.spaceapi.domain.Cache cache WHERE cache.space.id = :spaceId")
	Cache findBySpaces(@Param("spaceId") Long spaceId);
}
