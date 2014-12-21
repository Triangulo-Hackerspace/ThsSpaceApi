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

import br.net.triangulohackerspace.spaceapi.domain.Spacefed;
import br.net.triangulohackerspace.spaceapi.repository.SpacefedRepository;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.impl.SpacefedServiceImpl;
import br.net.triangulohackerspace.spaceapi.util.SpacefedUtil;

@RunWith(MockitoJUnitRunner.class)
public class SpacefedServiceImplTest {

	@Mock
	private SpacefedRepository spacefedRepository;

	private SpacefedService spacefedService;

	@Before
	public void setUp() throws Exception {
		spacefedService = new SpacefedServiceImpl(spacefedRepository);
	}

	@Test
	public void shouldSaveNewSpacefed_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedSpacefedShouldBeReturned()
			throws Exception {
		final Spacefed savedSpacefed = stubRepositoryToReturnSpacefedOnSave();
		final Spacefed spacefed = SpacefedUtil.createSpacefed();
		final Spacefed returnedSpacefed = spacefedService.save(spacefed);
		// verify repository was called with spacefed
		verify(spacefedRepository, times(1)).save(spacefed);
		assertEquals("Returned spacefed should come from the repository",
				savedSpacefed, returnedSpacefed);
	}

	@Test
	public void shouldSaveNewSpacefed_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown()
			throws Exception {
		stubRepositoryToReturnExistingSpacefed();
		try {
			final Spacefed spacefed = SpacefedUtil.createSpacefed();
			spacefedService.save(spacefed);
			fail("Expected exception");
		} catch (AlreadyExistsException ignored) {
		}
		verify(spacefedRepository, never()).save(any(Spacefed.class));
	}

	@Test
	public void shouldListAllSpacefeds_GivenThereExistSome_ThenTheCollectionShouldBeReturned()
			throws Exception {
		stubRepositoryToReturnExistingSpacefeds(1);
		Collection<Spacefed> list = spacefedService.getList();
		assertNotNull(list);
		assertEquals(1, list.size());
		verify(spacefedRepository, times(1)).findAll();
	}

	@Test
	public void shouldListAllSpacefeds_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned()
			throws Exception {
		stubRepositoryToReturnExistingSpacefeds(0);
		Collection<Spacefed> list = spacefedService.getList();
		assertNotNull(list);
		assertTrue(list.isEmpty());
		verify(spacefedRepository, times(1)).findAll();
	}

	private Spacefed stubRepositoryToReturnSpacefedOnSave() {
		final Spacefed spacefed = SpacefedUtil.createSpacefed();
		when(spacefedRepository.save(any(Spacefed.class))).thenReturn(spacefed);
		return spacefed;
	}

	private void stubRepositoryToReturnExistingSpacefed() {
		final Spacefed spacefed = SpacefedUtil.createSpacefed();
		when(spacefedRepository.findOne(spacefed.getId())).thenReturn(spacefed);
	}

	private void stubRepositoryToReturnExistingSpacefeds(int howMany) {

		when(spacefedRepository.findAll()).thenReturn(SpacefedUtil.createSpacefedList(howMany));
	}
}
