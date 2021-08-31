package com.vodafone.aliaksoy.assessment.exception;

/**
 * @author aaksoy
 *
 */

public class GarageParkingException extends RuntimeException {

	private static final long serialVersionUID = -6744169289171107805L;

	public GarageParkingException(String errorMessage) {
		super(errorMessage);
	}
}
