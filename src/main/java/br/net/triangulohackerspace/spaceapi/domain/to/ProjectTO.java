package br.net.triangulohackerspace.spaceapi.domain.to;

import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.Project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectTO {

	@JsonIgnore
	private Project project;

	@JsonProperty(value = "project")
	List<Project> projects;

	public ProjectTO() {
		super();
	}

	/**
	 * @param temperatures
	 */
	public ProjectTO(Project project, List<Project> projects) {
		super();
		this.project = project;
		if (this.project == null) {
			this.project = new Project();
		}
		this.projects = projects;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result
				+ ((projects == null) ? 0 : projects.hashCode());
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
		ProjectTO other = (ProjectTO) obj;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (projects == null) {
			if (other.projects != null)
				return false;
		} else if (!projects.equals(other.projects))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectTO [project=" + project + ", projects=" + projects + "]";
	}

}
