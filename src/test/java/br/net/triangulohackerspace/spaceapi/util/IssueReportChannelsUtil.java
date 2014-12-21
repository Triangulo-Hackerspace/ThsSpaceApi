package br.net.triangulohackerspace.spaceapi.util;

import java.util.ArrayList;
import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels;

public class IssueReportChannelsUtil {

	private static final String ISSUE_MAIL = "issue_mail";

	private IssueReportChannelsUtil() {
	}

	public static IssueReportChannels createIssueReportChannels() {
		return getIssueReportChannels();
	}

	public static List<IssueReportChannels> createIssueReportChannelsList(int howMany) {
		List<IssueReportChannels> issueReportChannelsList = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			issueReportChannelsList.add(getIssueReportChannels());
		}
		return issueReportChannelsList;
	}
	
	public static IssueReportChannels getIssueReportChannels() {
		return new IssueReportChannels(ISSUE_MAIL, SpaceUtil.getSpace());
	}

}
