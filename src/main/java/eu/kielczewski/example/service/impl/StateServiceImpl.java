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

import eu.kielczewski.example.domain.State;
import eu.kielczewski.example.repository.StateRepository;
import eu.kielczewski.example.service.BusinessService;
import eu.kielczewski.example.service.exception.AlreadyExistsException;

@Service
@Validated
public class StateServiceImpl implements BusinessService<State> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateServiceImpl.class);
    private final StateRepository repository;

    @Inject
    public StateServiceImpl(final StateRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public State save(@NotNull @Valid final State state) {
        LOGGER.debug("Creating {}", state);
        State existing = repository.findOne(state.getId());
        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a state with id=%s", state.getId()));
        }
        return repository.save(state);
    }

    @Override
    @Transactional(readOnly = true)
    public List<State> getList() {
		LOGGER.debug("Retrieving the list of all states");
        return repository.findAll();
    }

}
