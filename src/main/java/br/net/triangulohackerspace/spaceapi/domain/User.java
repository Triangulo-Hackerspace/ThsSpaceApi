package br.net.triangulohackerspace.spaceapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;

@Entity
public class User extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = 127178790025556234L;

	@NotNull
	@Size(max = 64)
	@Column(name = "username", nullable = false, updatable = false)
	private String username;

	@NotNull
	@Size(max = 64)
	@Column(name = "password", nullable = false)
	private String password;

	public User() {
		super();
	}

	public User(Long id) {
		super(id);
	}

	public User(Long id, String username, String password) {
		super(id);
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", super.getId())
				.add("username", username).add("password", password).toString();
	}
}
