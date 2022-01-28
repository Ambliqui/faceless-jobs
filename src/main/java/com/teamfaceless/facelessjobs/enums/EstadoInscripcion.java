package com.teamfaceless.facelessjobs.enums;

public enum EstadoInscripcion {
INSCRITO("Inscrito"),DESCARTADO("Descartado"),EN_PROCESO("En_Proceso");
	
	private String texto;

	EstadoInscripcion (String texto){
		this.texto=texto;
	}
	
	public String getTexto() {
		return texto;
	}
}
