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
import br.net.triangulohackerspace.spaceapi.service.IssueReportChannelsService;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.impl.IssueReportChannelsServiceImpl;
import br.net.triangulohackerspace.spaceapi.util.IssueReportChannelsUtil;

@RunWith(MockitoJUnitRunner.class)
public class IssueReportChannelsServiceImplTest {

    @Mock
    private IssueReportChannelsRepository issueReportChannelsRepository;

    private IssueReportChannelsService issueService;;

    @Before
    public void setUp() throws Exception {
    	issueService = new IssueReportChannelsServiceImpl(issueReportChannelsRepository);
    }

    @Test
    public void shouldSaveNewIssueReportChannels_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedIssueReportChannelsShouldBeReturned() throws Exception {
        final IssueReportChannels savedIssueReportChannels = stubRepositoryToReturnIssueReportChannelsOnSave();
        final IssueReportChannels issueReportChannels = IssueReportChannelsUtil.createIssueReportChannels();
        final IssueReportChannels returnedIssueReportChannels = issueService.save(issueReportChannels);
        // verify repository was called with issueReportChannels
        verify(issueReportChannelsRepository, times(1)).save(issueReportChannels);
        assertEquals("Returned issueReportChannels should come from the repository", savedIssueReportChannels, returnedIssueReportChannels);
    }

    private IssueReportChannels stubRepositoryToReturnIssueReportChannelsOnSave() {
        IssueReportChannels issueReportChannels = IssueReportChannelsUtil.createIssueReportChannels();
        when(issueReportChannelsRepository.save(any(IssueReportChannels.class))).thenReturn(issueReportChannels);
        return issueReportChannels;
    }

    @Test
    public void shouldSaveNewIssueReportChannels_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingIssueReportChannels();
        try {
        	issueService.save(IssueReportChannelsUtil.createIssueReportChannels());
            fail("Expected exception");
        } catch (AlreadyExistsException ignored) {
        }
        verify(issueReportChannelsRepository, never()).save(any(IssueReportChannels.class));
    }

    private void stubRepositoryToReturnExistingIssueReportChannels() {
        final IssueReportChannels issueReportChannels = IssueReportChannelsUtil.createIssueReportChannels();
        when(issueReportChannelsRepository.findOne(issueReportChannels.getId())).thenReturn(issueReportChannels);
    }

    @Test
    public void shouldListAllIssueReportChannelss_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingIssueReportChannelss(10);
        Collection<IssueReportChannels> list = issueService.getList();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(issueReportChannelsRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingIssueReportChannelss(int howMany) {
        when(issueReportChannelsRepository.findAll()).thenReturn(IssueReportChannelsUtil.createIssueReportChannelsList(howMany));
    }

    @Test
    public void shouldListAllIssueReportChannelss_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingIssueReportChannelss(0);
        Collection<IssueReportChannels> list = issueService.getList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(issueReportChannelsRepository, times(1)).findAll();
    }

}
