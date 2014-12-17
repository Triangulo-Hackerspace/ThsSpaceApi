package br.net.triangulohackerspace.spaceapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "space", nullable = false)
	private String space;

	@NotNull
	@Size(max = 64)
	@Column(name = "logo", nullable = false)
	private String logo;

	@NotNull
	@Size(max = 64)
	@Column(name = "url", nullable = false)
	private String url;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	@ManyToOne
	@JoinColumn(name = "spacefed_id")
	private Spacefed spacefed;
	
	@ManyToOne
	@JoinColumn(name = "contact_id")
	private Contact contact;
	
	@ManyToOne
	@JoinColumn(name = "issue_report_channels_id")
	private IssueReportChannels issueReportChannels;
	
	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "cache_id")
	private Cache cache;
	
	@ManyToOne
	@JoinColumn(name = "sensor_id")
	private Sensor sensor;

	public Space() {
		super();
	}

	public Space(Long id) {
		super(id);
	}

	public Space(Long id, String apiVersion, String space, String logo,
			String url, Location location, Spacefed spacefed, Contact contact,
			IssueReportChannels issueReportChannels, State state,
			Project project, Cache cache, Sensor sensor) { // [TODO] passar para
															// build
		super(id);
		this.apiVersion = apiVersion;
		this.space = space;
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

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Spacefed getSpacefed() {
		return spacefed;
	}

	public void setSpacefed(Spacefed spacefed) {
		this.spacefed = spacefed;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public IssueReportChannels getIssueReportChannels() {
		return issueReportChannels;
	}

	public void setIssueReportChannels(IssueReportChannels issueReportChannels) {
		this.issueReportChannels = issueReportChannels;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
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
		result = prime * result + ((space == null) ? 0 : space.hashCode());
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
		if (space == null) {
			if (other.space != null)
				return false;
		} else if (!space.equals(other.space))
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
				+ ", space=" + space + ", logo=" + logo + ", url=" + url + "]";
	}

}
