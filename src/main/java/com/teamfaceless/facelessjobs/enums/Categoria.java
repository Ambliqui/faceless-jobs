package com.teamfaceless.facelessjobs.enums;

public enum Categoria {
BLANDA("Blanda"),DURA("Dura");
	
	private String nombre;
	
	Categoria (String nombre){
		this.nombre=nombre;
	}

	public String getNombre() {
		return nombre;
	}	
}
