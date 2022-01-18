package com.teamfaceless.facelessjobs.exceptions;

import java.io.Serializable;

public class CIFExisteException extends Exception implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public CIFExisteException(String mensaje) {
		super(mensaje);
	}
	
}
