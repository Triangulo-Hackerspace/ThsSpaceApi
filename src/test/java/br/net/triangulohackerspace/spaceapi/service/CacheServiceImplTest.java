package br.net.triangulohackerspace.spaceapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.net.triangulohackerspace.spaceapi.domain.Cache;
import br.net.triangulohackerspace.spaceapi.repository.CacheRepository;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.impl.CacheServiceImpl;
import br.net.triangulohackerspace.spaceapi.util.CacheUtil;

@RunWith(MockitoJUnitRunner.class)
public class CacheServiceImplTest {

	@Mock
	private CacheRepository cacheRepository;

	private CacheService cacheService;

	@Before
	public void setUp() throws Exception {
		cacheService = new CacheServiceImpl(cacheRepository);
	}

	@Test
	public void shouldSaveNewCache_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedCacheShouldBeReturned()
			throws Exception {
		final Cache savedCache = stubRepositoryToReturnCacheOnSave();
		final Cache cache = CacheUtil.createCache();
		final Cache returnedCache = cacheService.save(cache);
		// verify repository was called with cache
		verify(cacheRepository, times(1)).save(cache);
		assertEquals("Returned cache should come from the repository",
				savedCache, returnedCache);
	}

	@Test
	public void shouldSaveNewCache_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown()
			throws Exception {
		stubRepositoryToReturnExistingCache();
		try {
			final Cache cache = CacheUtil.createCache();
			cacheService.save(cache);
			fail("Expected exception");
		} catch (AlreadyExistsException ignored) {
		}
		verify(cacheRepository, never()).save(any(Cache.class));
	}

	@Test
	public void shouldListAllCaches_GivenThereExistSome_ThenTheCollectionShouldBeReturned()
			throws Exception {
		stubRepositoryToReturnExistingCaches(1);
		Collection<Cache> list = cacheService.getList();
		assertNotNull(list);
		assertEquals(1, list.size());
		verify(cacheRepository, times(1)).findAll();
	}

	@Test
	public void shouldListAllCaches_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned()
			throws Exception {
		stubRepositoryToReturnExistingCaches(0);
		Collection<Cache> list = cacheService.getList();
		assertNotNull(list);
		assertTrue(list.isEmpty());
		verify(cacheRepository, times(1)).findAll();
	}

	private Cache stubRepositoryToReturnCacheOnSave() {
		final Cache cache = CacheUtil.createCache();
		when(cacheRepository.save(any(Cache.class))).thenReturn(cache);
		return cache;
	}

	private void stubRepositoryToReturnExistingCache() {
		final Cache cache = CacheUtil.createCache();
		when(cacheRepository.findOne(cache.getId())).thenReturn(cache);
	}

	private void stubRepositoryToReturnExistingCaches(int howMany) {

		when(cacheRepository.findAll()).thenReturn(
				CacheUtil.createCacheList(howMany));
	}

}
