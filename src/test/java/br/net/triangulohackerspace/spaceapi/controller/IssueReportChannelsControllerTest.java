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

import br.net.triangulohackerspace.spaceapi.controller.IssueReportChannelsController;
import br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels;
import br.net.triangulohackerspace.spaceapi.service.IssueReportChannelsService;
import br.net.triangulohackerspace.spaceapi.util.IssueReportChannelsUtil;

@RunWith(MockitoJUnitRunner.class)
public class IssueReportChannelsControllerTest {

    @Mock
    private IssueReportChannelsService issueService;

    @Inject
    private IssueReportChannelsController issueReportChannelsController;

    @Before
    public void setUp() throws Exception {
    	issueReportChannelsController = new IssueReportChannelsController(issueService);
    }

    @Test
    public void shouldCreateIssueReportChannels() throws Exception {
        final IssueReportChannels savedIssueReportChannels = stubServiceToReturnStoredIssueReportChannels();
        final IssueReportChannels issueReportChannels = IssueReportChannelsUtil.createIssueReportChannels();
        IssueReportChannels returnedIssueReportChannels = issueReportChannelsController.createIssueReportChannels(issueReportChannels);
        // verify issueReportChannels was passed to IssueReportChannelsService
        verify(issueService, times(1)).save(issueReportChannels);
        assertEquals("Returned issueReportChannels should come from the service", savedIssueReportChannels, returnedIssueReportChannels);
    }

    private IssueReportChannels stubServiceToReturnStoredIssueReportChannels() {
        final IssueReportChannels issueReportChannels = IssueReportChannelsUtil.createIssueReportChannels();
        when(issueService.save(any(IssueReportChannels.class))).thenReturn(issueReportChannels);
        return issueReportChannels;
    }


    @Test
    public void shouldListAllIssueReportChannelss() throws Exception {
        stubServiceToReturnExistingIssueReportChannelss(10);
        Collection<IssueReportChannels> issueReportChannelss = issueReportChannelsController.listIssueReportChannelss();
        assertNotNull(issueReportChannelss);
        assertEquals(10, issueReportChannelss.size());
        // verify issueReportChannels was passed to IssueReportChannelsService
        verify(issueService, times(1)).getList();
    }

    private void stubServiceToReturnExistingIssueReportChannelss(int howMany) {
        when(issueService.getList()).thenReturn(IssueReportChannelsUtil.createIssueReportChannelsList(howMany));
    }

}
