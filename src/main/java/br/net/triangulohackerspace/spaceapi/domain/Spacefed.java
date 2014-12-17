package br.net.triangulohackerspace.spaceapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Spacefed extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = 2174101510699610775L;

	@NotNull
	@Column(name = "spacenet", nullable = false)
	private Boolean spacenet;

	@NotNull
	@Column(name = "spacesaml", nullable = false)
	private Boolean spacesaml;

	@NotNull
	@Column(name = "spacephone", nullable = false)
	private Boolean spacephone;

	public Spacefed() {
		super();
	}

	public Spacefed(Long id) {
		super(id);
	}

	public Spacefed(Long id, Boolean spacenet, Boolean spacesaml,
			Boolean spacephone) {
		super(id);
		this.spacenet = spacenet;
		this.spacesaml = spacesaml;
		this.spacephone = spacephone;
	}

	public Boolean getSpacenet() {
		return spacenet;
	}

	public void setSpacenet(Boolean spacenet) {
		this.spacenet = spacenet;
	}

	public Boolean getSpacesaml() {
		return spacesaml;
	}

	public void setSpacesaml(Boolean spacesaml) {
		this.spacesaml = spacesaml;
	}

	public Boolean getSpacephone() {
		return spacephone;
	}

	public void setSpacephone(Boolean spacephone) {
		this.spacephone = spacephone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((spacenet == null) ? 0 : spacenet.hashCode());
		result = prime * result
				+ ((spacephone == null) ? 0 : spacephone.hashCode());
		result = prime * result
				+ ((spacesaml == null) ? 0 : spacesaml.hashCode());
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
		Spacefed other = (Spacefed) obj;
		if (spacenet == null) {
			if (other.spacenet != null)
				return false;
		} else if (!spacenet.equals(other.spacenet))
			return false;
		if (spacephone == null) {
			if (other.spacephone != null)
				return false;
		} else if (!spacephone.equals(other.spacephone))
			return false;
		if (spacesaml == null) {
			if (other.spacesaml != null)
				return false;
		} else if (!spacesaml.equals(other.spacesaml))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Spacefed [spacenet=" + spacenet + ", spacesaml=" + spacesaml
				+ ", spacephone=" + spacephone + "]";
	}

}
