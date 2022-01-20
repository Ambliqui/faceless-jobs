package com.teamfaceless.facelessjobs.enums;

public enum RangoEmpleados {
MICRO("Micro empresa","1-10"),PEQUEÑA("Empresa pequeña","10-50"),MEDIANA("Empresa Mediana","50-250"),GRANDE("Gran empresa","+250");
	
	private String titulo;
	private String tamaño;
	
	RangoEmpleados(String titulo, String tamaño){
		this.titulo=titulo;
		this.tamaño=tamaño;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getTamaño() {
		return tamaño;
	}
}
