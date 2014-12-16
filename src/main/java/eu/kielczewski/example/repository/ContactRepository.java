package eu.kielczewski.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.kielczewski.example.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
