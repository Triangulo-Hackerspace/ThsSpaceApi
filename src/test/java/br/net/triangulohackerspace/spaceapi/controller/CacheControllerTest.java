package br.net.triangulohackerspace.spaceapi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.net.triangulohackerspace.spaceapi.controller.CacheController;
import br.net.triangulohackerspace.spaceapi.domain.Cache;
import br.net.triangulohackerspace.spaceapi.service.CacheService;
import br.net.triangulohackerspace.spaceapi.util.CacheUtil;

@RunWith(MockitoJUnitRunner.class)
public class CacheControllerTest {

    @Mock
    private CacheService cacheService;

    @Inject
    private CacheController cacheController;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldCreateCache() throws Exception {
        final Cache savedCache = stubServiceToReturnStoredCache();
        final Cache cache = CacheUtil.createCache();
        Cache returnedCache = cacheController.createCache(cache);
        // verify cache was passed to CacheService
        verify(cacheService, times(1)).save(cache);
        assertEquals("Returned cache should come from the service", savedCache, returnedCache);
    }

    private Cache stubServiceToReturnStoredCache() {
        final Cache cache = CacheUtil.createCache();
        when(cacheService.save(any(Cache.class))).thenReturn(cache);
        return cache;
    }


    @Test
    public void shouldListAllCaches() throws Exception {
        stubServiceToReturnExistingCaches(10);
        Collection<Cache> caches = cacheController.listCaches();
        assertNotNull(caches);
        assertEquals(10, caches.size());
        // verify cache was passed to CacheService
        verify(cacheService, times(1)).getList();
    }

    private void stubServiceToReturnExistingCaches(int howMany) {
        when(cacheService.getList()).thenReturn(CacheUtil.createCacheList(howMany));
    }

}
