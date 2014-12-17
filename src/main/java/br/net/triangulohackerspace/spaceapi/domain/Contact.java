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
public class Contact extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = 2429027949871143439L;

	@NotNull
	@Size(max = 64)
	@Column(name = "email", nullable = false)
	private String email;

	@NotNull
	@Size(max = 64)
	@Column(name = "twitter", nullable = false)
	private String twitter;

	@NotNull
	@Size(max = 64)
	@Column(name = "irc", nullable = false)
	private String irc;

	@NotNull
	@Size(max = 64)
	@Column(name = "ml", nullable = false)
	private String ml;

	@NotNull
	@Size(max = 64)
	@Column(name = "issue_mail", nullable = false)
	private String issueMail;
	
	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Space> spaces = new LinkedList<Space>();

	public Contact() {
		super();
	}

	public Contact(Long id) {
		super(id);
	}

	public Contact(Long id, String email, String twitter, String irc,
			String ml, String issueMail) { // [TODO] Criar Build
		super(id);
		this.email = email;
		this.twitter = twitter;
		this.irc = irc;
		this.ml = ml;
		this.issueMail = issueMail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getIrc() {
		return irc;
	}

	public void setIrc(String irc) {
		this.irc = irc;
	}

	public String getMl() {
		return ml;
	}

	public void setMl(String ml) {
		this.ml = ml;
	}

	public String getIssueMail() {
		return issueMail;
	}

	public void setIssueMail(String issueMail) {
		this.issueMail = issueMail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((irc == null) ? 0 : irc.hashCode());
		result = prime * result
				+ ((issueMail == null) ? 0 : issueMail.hashCode());
		result = prime * result + ((ml == null) ? 0 : ml.hashCode());
		result = prime * result + ((twitter == null) ? 0 : twitter.hashCode());
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
		Contact other = (Contact) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (irc == null) {
			if (other.irc != null)
				return false;
		} else if (!irc.equals(other.irc))
			return false;
		if (issueMail == null) {
			if (other.issueMail != null)
				return false;
		} else if (!issueMail.equals(other.issueMail))
			return false;
		if (ml == null) {
			if (other.ml != null)
				return false;
		} else if (!ml.equals(other.ml))
			return false;
		if (twitter == null) {
			if (other.twitter != null)
				return false;
		} else if (!twitter.equals(other.twitter))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [email=" + email + ", twitter=" + twitter + ", irc="
				+ irc + ", ml=" + ml + ", issueMail=" + issueMail + "]";
	}

}
