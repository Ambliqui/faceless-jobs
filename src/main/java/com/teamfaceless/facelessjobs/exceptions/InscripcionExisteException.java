package com.teamfaceless.facelessjobs.exceptions;

import java.io.Serializable;

public class InscripcionExisteException extends Exception implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public InscripcionExisteException(String mensaje) {
		super(mensaje);
	}
	
}
