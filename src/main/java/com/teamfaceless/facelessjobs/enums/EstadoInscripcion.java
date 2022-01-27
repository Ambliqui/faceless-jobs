package com.teamfaceless.facelessjobs.enums;

public enum EstadoInscripcion {
INSCRITO("Inscrito"),DESCARTADO("Descartado"),EN_PROCESO("En Proceso");
	
	private String texto;

	EstadoInscripcion (String texto){
		this.texto=texto;
	}
	
	public String getTexto() {
		return texto;
	}
}
