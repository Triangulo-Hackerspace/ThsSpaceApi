package br.net.triangulohackerspace.spaceapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class State extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = 5470957854048224005L;

	@NotNull
	@Column(name = "open", nullable = false)
	private Boolean open;

	public State() {
		super();
	}

	public State(Long id) {
		super(id);
	}

	public State(Long id, Boolean open) {
		super(id);
		this.open = open;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((open == null) ? 0 : open.hashCode());
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
		if (open == null) {
			if (other.open != null)
				return false;
		} else if (!open.equals(other.open))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "State [open=" + open + "]";
	}

}
