package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.triangulohackerspace.spaceapi.domain.Space;

public interface SpaceRepository extends JpaRepository<Space, Long> {
}
