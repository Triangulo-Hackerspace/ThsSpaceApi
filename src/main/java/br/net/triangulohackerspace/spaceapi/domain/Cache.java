package br.net.triangulohackerspace.spaceapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("new")
public class Cache extends AbstractPersistable<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 64)
	@Column(name = "schedule", nullable = false)
	private String schedule;

	@ManyToOne
	@JoinColumn(name = "space_id")
	@JsonIgnore
	private Space space;

	public Cache() {
		super();
	}
	
	/**
	 * @param schedule
	 * @param space
	 */
	public Cache(String schedule, Space space) {
		this.schedule = schedule;
		this.space = space;
	}
	
	@Override
	@JsonIgnore
	public Long getId() {
		return super.getId();
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
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
				+ ((schedule == null) ? 0 : schedule.hashCode());
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
		Cache other = (Cache) obj;
		if (schedule == null) {
			if (other.schedule != null)
				return false;
		} else if (!schedule.equals(other.schedule))
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
		return "Cache [schedule=" + schedule + ", space=" + space + "]";
	}

}
