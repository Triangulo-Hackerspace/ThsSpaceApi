package br.net.triangulohackerspace.spaceapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Project extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = 8475684755570732926L;

	@NotNull
	@Size(max = 64)
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Size(max = 64)
	@Column(name = "git", nullable = false)
	private String git;

	public Project() {
		super();
	}

	public Project(Long id) {
		super(id);
	}

	public Project(Long id, String name, String git) {
		super(id);
		this.name = name;
		this.git = git;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGit() {
		return git;
	}

	public void setGit(String git) {
		this.git = git;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((git == null) ? 0 : git.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Project other = (Project) obj;
		if (git == null) {
			if (other.git != null)
				return false;
		} else if (!git.equals(other.git))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", git=" + git + "]";
	}

}
