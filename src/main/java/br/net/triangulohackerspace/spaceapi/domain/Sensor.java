package br.net.triangulohackerspace.spaceapi.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Sensor extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = -5439912294977186010L;

	@NotNull
	@Size(max = 64)
	@Column(name = "sensor_name", nullable = false)
	@JsonIgnore
	private String name;

	@ManyToOne
	@JoinColumn(name = "temperature_id")
	private Temperature temperature;
	
	@OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Space> sensors = new LinkedList<Space>();

	public Sensor() {
		super();
	}

	public Sensor(Long id) {
		super(id);
	}

	public Sensor(Long id, String name, Temperature temperature) {
		super(id);
		this.name = name;
		this.temperature = temperature;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Temperature getTemperature() {
		return temperature;
	}

	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((temperature == null) ? 0 : temperature.hashCode());
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
		Sensor other = (Sensor) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sensor [name=" + name + ", temperature=" + temperature + "]";
	}

}
