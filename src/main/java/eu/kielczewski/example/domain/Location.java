package eu.kielczewski.example.domain;

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
	@Size(max = 64)
	@Column(name = "lon", nullable = false)
	private String lat;

	public Location(Long id, String address, String lat) {
		super(id);
		this.address = address;
		this.lat = lat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
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
		return true;
	}

	@Override
	public String toString() {
		return "Location [address=" + address + ", lat=" + lat + "]";
	}

}
