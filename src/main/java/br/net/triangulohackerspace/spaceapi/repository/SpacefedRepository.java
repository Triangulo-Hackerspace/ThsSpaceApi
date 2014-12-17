package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.triangulohackerspace.spaceapi.domain.Spacefed;

public interface SpacefedRepository extends JpaRepository<Spacefed, Long> {
}
