package br.net.triangulohackerspace.spaceapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class IssueReportChannels extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = 4869919303001760198L;

	@NotNull
	@Size(max = 64)
	@Column(name = "issue_mail", nullable = false)
	private String issueMail;

	@ManyToOne
	@JoinColumn(name = "space_id")
	@JsonIgnore
	private Space space;

	public IssueReportChannels() {
		super();
	}

	public IssueReportChannels(Long id) {
		super(id);
	}

	public IssueReportChannels(Long id, String issueMail, Space space) {
		super(id);
		this.issueMail = issueMail;
		this.space = space;
	}

	public String getIssueMail() {
		return issueMail;
	}

	public void setIssueMail(String issueMail) {
		this.issueMail = issueMail;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((issueMail == null) ? 0 : issueMail.hashCode());
		result = prime * result + ((space == null) ? 0 : space.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IssueReportChannels other = (IssueReportChannels) obj;
		if (issueMail == null) {
			if (other.issueMail != null)
				return false;
		} else if (!issueMail.equals(other.issueMail))
			return false;
		if (space == null) {
			if (other.space != null)
				return false;
		} else if (!space.equals(other.space))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IssueReportChannels [issueMail=" + issueMail + ", space="
				+ space + "]";
	}

}
