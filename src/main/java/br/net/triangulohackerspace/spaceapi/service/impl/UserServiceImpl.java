package br.net.triangulohackerspace.spaceapi.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.net.triangulohackerspace.spaceapi.domain.User;
import br.net.triangulohackerspace.spaceapi.repository.UserRepository;
import br.net.triangulohackerspace.spaceapi.service.UserService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository repository;

    @Inject
    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User save(@NotNull @Valid final User user) {
        LOGGER.debug("Creating {}", user);
        User existing = repository.findOne(user.getId());
        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a user with id=%s", user.getId()));
        }
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        LOGGER.debug("Retrieving the list of all users");
        return repository.findAll();
    }

}
