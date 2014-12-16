package eu.kielczewski.example.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import eu.kielczewski.example.domain.Contact;
import eu.kielczewski.example.repository.ContactRepository;
import eu.kielczewski.example.service.BusinessService;
import eu.kielczewski.example.service.exception.AlreadyExistsException;

@Service
@Validated
public class ContactServiceImpl implements BusinessService<Contact> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactServiceImpl.class);
    private final ContactRepository repository;

    @Inject
    public ContactServiceImpl(final ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Contact save(@NotNull @Valid final Contact Contact) {
        LOGGER.debug("Creating {}", Contact);
        Contact existing = repository.findOne(Contact.getId());
        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a Contact with id=%s", Contact.getId()));
        }
        return repository.save(Contact);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> getList() {
        LOGGER.debug("Retrieving the list of all Contacts");
        return repository.findAll();
    }

}
