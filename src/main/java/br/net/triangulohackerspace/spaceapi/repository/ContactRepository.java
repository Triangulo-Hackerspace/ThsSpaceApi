package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.net.triangulohackerspace.spaceapi.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	@Query(value = "SELECT contact FROM br.net.triangulohackerspace.spaceapi.domain.Contact contact WHERE contact.space.id = :spaceId")
	Contact findBySpaces(@Param("spaceId") Long spaceId);
}
