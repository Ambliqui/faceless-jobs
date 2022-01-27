package com.teamfaceless.facelessjobs.enums;

public enum EstadoOferta {
ACTIVA("Activa"),ARCHIVADA("Archivada");
	
	private String texto;
	
	EstadoOferta (String texto){
		this.texto=texto;
	}
	
	public String getTexto() {
		return texto;
	}
}
