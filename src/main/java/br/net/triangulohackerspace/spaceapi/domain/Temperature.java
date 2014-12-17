package br.net.triangulohackerspace.spaceapi.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Temperature extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = -9084396676085892892L;

	@NotNull
	@Size(max = 24)
	@Column(name = "value", nullable = false)
	private String value;

	@NotNull
	@Size(max = 24)
	@Column(name = "unit", nullable = false)
	private String unit; // "Roof"

	@Size(max = 24)
	@Column(name = "location", nullable = false)
	private String location; // "°C"
	
	@OneToMany(mappedBy = "temperature", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Sensor> sensors = new LinkedList<Sensor>();

	public Temperature() {
		super();
	}

	public Temperature(Long id, String value, String unit, String location) {
		super(id);
		this.value = value;
		this.unit = unit;
		this.location = location;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Temperature other = (Temperature) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Temperature [value=" + value + ", unit=" + unit + ", location="
				+ location + "]";
	}

}
