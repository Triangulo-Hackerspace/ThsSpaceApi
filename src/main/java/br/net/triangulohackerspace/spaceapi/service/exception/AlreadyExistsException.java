package br.net.triangulohackerspace.spaceapi.service.exception;

public class AlreadyExistsException extends RuntimeException {

  private static final long serialVersionUID = 1860746449529240646L;

	public AlreadyExistsException(final String message) {
        super(message);
    }
}
