package eu.kielczewski.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Space extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = 8883842678258104959L;

	@NotNull
	@Size(max = 64)
	@Column(name = "api_version", nullable = false)
	private String apiVersion;

	@NotNull
	@Size(max = 64)
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Size(max = 64)
	@Column(name = "logo", nullable = false)
	private String logo;

	@NotNull
	@Size(max = 64)
	@Column(name = "url", nullable = false)
	private String url;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "spacefed_id")
	private Spacefed spacefed;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id")
	private Contact contact;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issue_report_channels_id")
	private IssueReportChannels issueReportChannels;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	private State state;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cache_id")
	private Cache cache;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sensor_id")
	private Sensor sensor;

	public Space(Long id, String apiVersion, String name, String logo,
			String url, Location location, Spacefed spacefed, Contact contact,
			IssueReportChannels issueReportChannels, State state,
			Project project, Cache cache, Sensor sensor) { // [TODO] passar para
															// build
		super(id);
		this.apiVersion = apiVersion;
		this.name = name;
		this.logo = logo;
		this.url = url;
		this.location = location;
		this.spacefed = spacefed;
		this.contact = contact;
		this.issueReportChannels = issueReportChannels;
		this.state = state;
		this.project = project;
		this.cache = cache;
		this.sensor = sensor;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apiVersion == null) ? 0 : apiVersion.hashCode());
		result = prime * result + ((cache == null) ? 0 : cache.hashCode());
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime
				* result
				+ ((issueReportChannels == null) ? 0 : issueReportChannels
						.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((logo == null) ? 0 : logo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
		result = prime * result
				+ ((spacefed == null) ? 0 : spacefed.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Space other = (Space) obj;
		if (apiVersion == null) {
			if (other.apiVersion != null)
				return false;
		} else if (!apiVersion.equals(other.apiVersion))
			return false;
		if (cache == null) {
			if (other.cache != null)
				return false;
		} else if (!cache.equals(other.cache))
			return false;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (issueReportChannels == null) {
			if (other.issueReportChannels != null)
				return false;
		} else if (!issueReportChannels.equals(other.issueReportChannels))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (logo == null) {
			if (other.logo != null)
				return false;
		} else if (!logo.equals(other.logo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (sensor == null) {
			if (other.sensor != null)
				return false;
		} else if (!sensor.equals(other.sensor))
			return false;
		if (spacefed == null) {
			if (other.spacefed != null)
				return false;
		} else if (!spacefed.equals(other.spacefed))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Space [id=" + super.getId() + ", apiVersion=" + apiVersion
				+ ", name=" + name + ", logo=" + logo + ", url=" + url + "]";
	}

}
