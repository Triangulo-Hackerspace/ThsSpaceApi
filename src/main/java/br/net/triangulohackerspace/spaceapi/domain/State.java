package br.net.triangulohackerspace.spaceapi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("new")
public class State extends AbstractPersistable<Long> implements Serializable {

	private static final long serialVersionUID = 5470957854048224005L;

	@NotNull
	@Column(name = "open", nullable = false)
	private Boolean open;

	@ManyToOne
	@JoinColumn(name = "space_id")
	private Space space;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Date date;

	private String stateStatus;

	public State() {
		super();
	}

	public State(Boolean open, Space space, User user, Date date,
			String stateStatus) {
		this.open = open;
		this.space = space;
		this.user = user;
		this.date = date;
		this.stateStatus = stateStatus;
	}

	@Override
	@JsonIgnore
	public Long getId() {
		return super.getId();
	}
	
	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStateStatus() {
		return stateStatus;
	}

	public void setStateStatus(String stateStatus) {
		this.stateStatus = stateStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((open == null) ? 0 : open.hashCode());
		result = prime * result + ((space == null) ? 0 : space.hashCode());
		result = prime * result
				+ ((stateStatus == null) ? 0 : stateStatus.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		State other = (State) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (open == null) {
			if (other.open != null)
				return false;
		} else if (!open.equals(other.open))
			return false;
		if (space == null) {
			if (other.space != null)
				return false;
		} else if (!space.equals(other.space))
			return false;
		if (stateStatus == null) {
			if (other.stateStatus != null)
				return false;
		} else if (!stateStatus.equals(other.stateStatus))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "State [open=" + open + ", space=" + space + ", user=" + user
				+ ", date=" + date + ", stateStatus=" + stateStatus + "]";
	}

}
