package com.teamfaceless.facelessjobs.exceptions;

import java.io.Serializable;

public class EmailExisteException extends Exception implements Serializable{


	private static final long serialVersionUID = 1L;

	public EmailExisteException(String mensaje) {
		super(mensaje);
	}
	
}
