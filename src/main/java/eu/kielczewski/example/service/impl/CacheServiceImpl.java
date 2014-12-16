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

import eu.kielczewski.example.domain.Cache;
import eu.kielczewski.example.repository.CacheRepository;
import eu.kielczewski.example.service.BusinessService;
import eu.kielczewski.example.service.exception.AlreadyExistsException;

@Service
@Validated
public class CacheServiceImpl implements BusinessService<Cache> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);
    private final CacheRepository repository;

    @Inject
    public CacheServiceImpl(final CacheRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Cache save(@NotNull @Valid final Cache cache) {
        LOGGER.debug("Creating {}", cache);
        Cache existing = repository.findOne(cache.getId());
        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a cache with id=%s", cache.getId()));
        }
        return repository.save(cache);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cache> getList() {
		LOGGER.debug("Retrieving the list of all caches");
        return repository.findAll();
    }

}
