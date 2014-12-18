package br.net.triangulohackerspace.spaceapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@ManyToOne
	@JoinColumn(name = "space_id")
	@JsonIgnore
	private Space space;

	public Spacefed() {
		super();
	}

	public Spacefed(Long id) {
		super(id);
	}

	/**
	 * @param spacenet
	 * @param spacesaml
	 * @param spacephone
	 * @param space
	 */
	public Spacefed(Long id, Boolean spacenet, Boolean spacesaml, Boolean spacephone,
			Space space) {
		super(id);
		this.spacenet = spacenet;
		this.spacesaml = spacesaml;
		this.spacephone = spacephone;
		this.space = space;
	}

	/**
	 * @return the spacenet
	 */
	public Boolean getSpacenet() {
		return spacenet;
	}

	/**
	 * @param spacenet
	 *            the spacenet to set
	 */
	public void setSpacenet(Boolean spacenet) {
		this.spacenet = spacenet;
	}

	/**
	 * @return the spacesaml
	 */
	public Boolean getSpacesaml() {
		return spacesaml;
	}

	/**
	 * @param spacesaml
	 *            the spacesaml to set
	 */
	public void setSpacesaml(Boolean spacesaml) {
		this.spacesaml = spacesaml;
	}

	/**
	 * @return the spacephone
	 */
	public Boolean getSpacephone() {
		return spacephone;
	}

	/**
	 * @param spacephone
	 *            the spacephone to set
	 */
	public void setSpacephone(Boolean spacephone) {
		this.spacephone = spacephone;
	}

	/**
	 * @return the space
	 */
	public Space getSpace() {
		return space;
	}

	/**
	 * @param space
	 *            the space to set
	 */
	public void setSpace(Space space) {
		this.space = space;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Spacefed [spacenet=" + spacenet + ", spacesaml=" + spacesaml
				+ ", spacephone=" + spacephone + ", space=" + space + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((space == null) ? 0 : space.hashCode());
		result = prime * result
				+ ((spacenet == null) ? 0 : spacenet.hashCode());
		result = prime * result
				+ ((spacephone == null) ? 0 : spacephone.hashCode());
		result = prime * result
				+ ((spacesaml == null) ? 0 : spacesaml.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spacefed other = (Spacefed) obj;
		if (space == null) {
			if (other.space != null)
				return false;
		} else if (!space.equals(other.space))
			return false;
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

}
