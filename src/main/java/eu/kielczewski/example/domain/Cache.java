package eu.kielczewski.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cache extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(max = 64)
	@Column(name = "schedule", nullable = false)
	private String schedule;

	public Cache(Long id, String schedule) {
		super(id);
		this.schedule = schedule;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((schedule == null) ? 0 : schedule.hashCode());
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
		return true;
	}

	@Override
	public String toString() {
		return "Cache [id=" + super.getId() + ", schedule=" + schedule + "]";
	}
}
