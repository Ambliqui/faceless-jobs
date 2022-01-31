package com.teamfaceless.facelessjobs.enums;

public enum MedioFavorito {
CORREO("Correo Electrónico"),WHATSAPP("Whatsapp"),TELEFONO("Teléfono");

	private String dato;
	
	MedioFavorito(String dato){
		this.dato=dato;
	}
	
	public String getDato() {
		return this.dato;
	}
	
	public int getId() {
		return this.ordinal();
	}
}
