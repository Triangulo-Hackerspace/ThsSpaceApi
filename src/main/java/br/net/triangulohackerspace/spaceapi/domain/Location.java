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
public class Location extends AbstractPersistable<Long> implements Serializable {

	private static final long serialVersionUID = 7993128997393503166L;

	@NotNull
	@Size(max = 64)
	@Column(name = "address", nullable = false)
	private String address;

	@NotNull
	@Column(name = "lat", nullable = false)
	private Double lat;

	@NotNull
	@Column(name = "log", nullable = false)
	private Double log;

	@ManyToOne
	@JoinColumn(name = "space_id")
	@JsonIgnore
	private Space space;

	public Location() {
		super();
	}

	public Location(String address, Double lat, Double log, Space space) {
		this.address = address;
		this.lat = lat;
		this.log = log;
		this.space = space;
	}
	
	@Override
	@JsonIgnore
	public Long getId() {
		return super.getId();
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the lat
	 */
	public Double getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}

	/**
	 * @return the log
	 */
	public Double getLog() {
		return log;
	}

	/**
	 * @param log
	 *            the log to set
	 */
	public void setLog(Double log) {
		this.log = log;
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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((log == null) ? 0 : log.hashCode());
		result = prime * result + ((space == null) ? 0 : space.hashCode());
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
		Location other = (Location) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (log == null) {
			if (other.log != null)
				return false;
		} else if (!log.equals(other.log))
			return false;
		if (space == null) {
			if (other.space != null)
				return false;
		} else if (!space.equals(other.space))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Location [address=" + address + ", lat=" + lat + ", log=" + log
				+ ", space=" + space + "]";
	}

}
