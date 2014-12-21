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

import br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels;
import br.net.triangulohackerspace.spaceapi.repository.IssueReportChannelsRepository;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.impl.IssueReportChannelsServiceImpl;
import br.net.triangulohackerspace.spaceapi.util.IssueReportChannelsUtil;

@RunWith(MockitoJUnitRunner.class)
public class IssueReportChannelsServiceImplTest {

	@Mock
	private IssueReportChannelsRepository issueReportChannelsRepository;

	private IssueReportChannelsService issueReportChannelsService;

	@Before
	public void setUp() throws Exception {
		issueReportChannelsService = new IssueReportChannelsServiceImpl(issueReportChannelsRepository);
	}

	@Test
	public void shouldSaveNewIssueReportChannels_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedIssueReportChannelsShouldBeReturned()
			throws Exception {
		final IssueReportChannels savedIssueReportChannels = stubRepositoryToReturnIssueReportChannelsOnSave();
		final IssueReportChannels issueReportChannels = IssueReportChannelsUtil.createIssueReportChannels();
		final IssueReportChannels returnedIssueReportChannels = issueReportChannelsService.save(issueReportChannels);
		// verify repository was called with issueReportChannels
		verify(issueReportChannelsRepository, times(1)).save(issueReportChannels);
		assertEquals("Returned issueReportChannels should come from the repository",
				savedIssueReportChannels, returnedIssueReportChannels);
	}

	@Test
	public void shouldSaveNewIssueReportChannels_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown()
			throws Exception {
		stubRepositoryToReturnExistingIssueReportChannels();
		try {
			final IssueReportChannels issueReportChannels = IssueReportChannelsUtil.createIssueReportChannels();
			issueReportChannelsService.save(issueReportChannels);
			fail("Expected exception");
		} catch (AlreadyExistsException ignored) {
		}
		verify(issueReportChannelsRepository, never()).save(any(IssueReportChannels.class));
	}

	@Test
	public void shouldListAllIssueReportChannelss_GivenThereExistSome_ThenTheCollectionShouldBeReturned()
			throws Exception {
		stubRepositoryToReturnExistingIssueReportChannelss(1);
		Collection<IssueReportChannels> list = issueReportChannelsService.getList();
		assertNotNull(list);
		assertEquals(1, list.size());
		verify(issueReportChannelsRepository, times(1)).findAll();
	}

	@Test
	public void shouldListAllIssueReportChannelss_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned()
			throws Exception {
		stubRepositoryToReturnExistingIssueReportChannelss(0);
		Collection<IssueReportChannels> list = issueReportChannelsService.getList();
		assertNotNull(list);
		assertTrue(list.isEmpty());
		verify(issueReportChannelsRepository, times(1)).findAll();
	}

	private IssueReportChannels stubRepositoryToReturnIssueReportChannelsOnSave() {
		final IssueReportChannels issueReportChannels = IssueReportChannelsUtil.createIssueReportChannels();
		when(issueReportChannelsRepository.save(any(IssueReportChannels.class))).thenReturn(issueReportChannels);
		return issueReportChannels;
	}

	private void stubRepositoryToReturnExistingIssueReportChannels() {
		final IssueReportChannels issueReportChannels = IssueReportChannelsUtil.createIssueReportChannels();
		when(issueReportChannelsRepository.findOne(issueReportChannels.getId())).thenReturn(issueReportChannels);
	}

	private void stubRepositoryToReturnExistingIssueReportChannelss(int howMany) {

		when(issueReportChannelsRepository.findAll()).thenReturn(IssueReportChannelsUtil.createIssueReportChannelsList(howMany));
	}

	

}
