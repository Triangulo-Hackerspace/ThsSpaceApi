package br.net.triangulohackerspace.spaceapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Location extends AbstractDomain implements Serializable {

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

	public Location() {
		super();
	}

	public Location(Long id) {
		super(id);
	}

	public Location(Long id, String address, Double lat, Double log) {
		super(id);
		this.address = address;
		this.lat = lat;
		this.log = log;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLog() {
		return log;
	}

	public void setLog(Double log) {
		this.log = log;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((log == null) ? 0 : log.hashCode());
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
		return true;
	}

	@Override
	public String toString() {
		return "Location [address=" + address + ", lat=" + lat + ", log=" + log
				+ "]";
	}

}
