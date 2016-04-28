package it.mesis.avis.exception;

import org.springframework.security.core.AuthenticationException;

public class StatusException extends AuthenticationException {

	private static final long serialVersionUID = 4208749398556682561L;
	
	public StatusException(String msg) {
		super(msg);
	}
	

}
