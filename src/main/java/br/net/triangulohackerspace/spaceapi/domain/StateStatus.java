package br.net.triangulohackerspace.spaceapi.domain;

public enum StateStatus {
	OPEN(true), CLOSE(false);

	private Boolean status;

	private StateStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
