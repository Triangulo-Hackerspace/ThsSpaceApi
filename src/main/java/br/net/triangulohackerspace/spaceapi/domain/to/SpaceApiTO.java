package br.net.triangulohackerspace.spaceapi.domain.to;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.net.triangulohackerspace.spaceapi.domain.Cache;
import br.net.triangulohackerspace.spaceapi.domain.Contact;
import br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels;
import br.net.triangulohackerspace.spaceapi.domain.Location;
import br.net.triangulohackerspace.spaceapi.domain.Project;
import br.net.triangulohackerspace.spaceapi.domain.Sensor;
import br.net.triangulohackerspace.spaceapi.domain.Space;
import br.net.triangulohackerspace.spaceapi.domain.Spacefed;
import br.net.triangulohackerspace.spaceapi.domain.State;

public class SpaceApiTO {
	
	@JsonIgnore
	private Space space;

	private Location location;

	private Spacefed spacefed;

	private Contact contact;

	private IssueReportChannels issueReportChannels;

	private State state;

	private Project project;

	private Cache cache;

	private Sensor sensor;
	
	
	public SpaceApiTO(Space space) {
		super();
		this.space = space;
		if (this.space == null) {
			this.space = new Space();
		}
	}

	public String getApiVersion() {
		return this.space.getApiVersion();
	}

	public void setApiVersion(String apiVersion) {
		this.space.setApiVersion(apiVersion);
	}

	public String getName() {
		return this.space.getName();
	}

	public void setName(String name) {
		this.space.setName(name);
	}

	public String getLogo() {
		return this.space.getLogo();
	}

	public void setLogo(String logo) {
		this.space.setLogo(logo);
	}

	public String getUrl() {
		return this.space.getUrl();
	}

	public void setUrl(String url) {
		this.space.setUrl(url);
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

}
