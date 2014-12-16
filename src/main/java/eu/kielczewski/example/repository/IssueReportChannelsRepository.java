package eu.kielczewski.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.kielczewski.example.domain.IssueReportChannels;

public interface IssueReportChannelsRepository extends JpaRepository<IssueReportChannels, Long> {
}
