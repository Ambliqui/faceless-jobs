package com.teamfaceless.facelessjobs.enums;

public enum EstadoOferta {
ACTIVA("Activa"),DESACTIVADA("Desactivada"),CERRADA("Cerrada");
	
	private String texto;
	
	EstadoOferta (String texto){
		this.texto=texto;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public int getId() {
		return this.ordinal();
	}
}
