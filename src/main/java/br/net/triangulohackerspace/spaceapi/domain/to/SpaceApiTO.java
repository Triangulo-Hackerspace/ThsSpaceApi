package br.net.triangulohackerspace.spaceapi.domain.to;

import br.net.triangulohackerspace.spaceapi.domain.Cache;
import br.net.triangulohackerspace.spaceapi.domain.Contact;
import br.net.triangulohackerspace.spaceapi.domain.IssueReportChannels;
import br.net.triangulohackerspace.spaceapi.domain.Location;
import br.net.triangulohackerspace.spaceapi.domain.Space;
import br.net.triangulohackerspace.spaceapi.domain.Spacefed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = { "api", "space", "logo", "url", "location",
		"spacefed", "contact", "issueReportChannels", "state", "project",
		"cache", "sensor" })
public class SpaceApiTO {

	@JsonIgnore
	private Space space;

	private Location location;

	private Spacefed spacefed;

	private Contact contact;

	private IssueReportChannels issueReportChannels;

	@JsonProperty(value = "state")
	private StateTO stateTO;

	@JsonProperty(value = "projects")
	private ProjectTO projectTO;// [TODO] Passar para project TO - Trazer lista
								// de projetos

	private Cache cache;

	@JsonProperty(value = "sensors")
	private SensorTO sensorTO;

	public SpaceApiTO(Space space) {
		super();
		this.space = space;
		if (this.space == null) {
			this.space = new Space();
		}
	}

	/**
	 * @return the apiVersion
	 */
	@JsonProperty(value = "api")
	public String getApiVersion() {
		return this.space.getApiVersion();
	}

	/**
	 * @param apiVersion
	 *            the apiVersion to set
	 */
	public void setApiVersion(String apiVersion) {
		this.space.setApiVersion(apiVersion);
	}

	/**
	 * @return the name
	 */
	@JsonProperty(value = "space")
	public String getName() {
		return this.space.getName();
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.space.setName(name);
	}

	/**
	 * @return the logo
	 */
	public String getLogo() {
		return this.space.getLogo();
	}

	/**
	 * @param logo
	 *            the logo to set
	 */
	public void setLogo(String logo) {
		this.space.setLogo(logo);
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return this.space.getUrl();
	}

	/**
	 * @param url
	 *            the url to set
	 */
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

	public StateTO getStateTO() {
		return stateTO;
	}

	public void setStateTO(StateTO stateTO) {
		this.stateTO = stateTO;
	}

	public ProjectTO getProjectTO() {
		return projectTO;
	}

	public void setProjectTO(ProjectTO projectTO) {
		this.projectTO = projectTO;
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
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

	/**
	 * @return the sensorTO
	 */
	public SensorTO getSensorTO() {
		return sensorTO;
	}

	/**
	 * @param sensorTO
	 *            the sensorTO to set
	 */
	public void setSensorTO(SensorTO sensorTO) {
		this.sensorTO = sensorTO;
	}

}
