package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels;

public interface IssueReportChannelsRepository extends JpaRepository<IssueReportChannels, Long> {
	
	@Query(value = "SELECT issueReportChannels FROM br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels issueReportChannels WHERE issueReportChannels.space.id = :spaceId")
	IssueReportChannels findBySpaces(@Param("spaceId") Long spaceId);
}
