package br.net.triangulohackerspace.spaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels;

public interface IssueReportChannelsRepository extends JpaRepository<IssueReportChannels, Long> {
}
