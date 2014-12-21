package br.net.triangulohackerspace.spaceapi.domain;

public enum StateStatus {
	OPEN("Open"), CLOSE("Close");

	private String stateStatus;

	private StateStatus(String stateStatus) {
		this.stateStatus = stateStatus;
	}

	public String getStateStatus() {
		return stateStatus;
	}

	public void setStateStatus(String stateStatus) {
		this.stateStatus = stateStatus;
	}

}
