package com.teamfaceless.facelessjobs.enums;

public enum Provincias {
	ÁLAVA(1,"Araba/Álava"),
	ALBACETE(2,"Albacete"),
	ALICANTE(3,"Alicante/Alacant"),
	ALMERÍA(4,"Almería"),
	ASTURIAS(33,"Asturias"),
	ÁVILA(5,"Ávila"),
	BADAJOZ(6,"Badajoz"),
	BALEARS(7,"Balears, Illes"),
	BARCELONA(8,"Barcelona"),
	BIZKAIA(48,"Bizkaia"),
	BURGOS(9,"Burgos"),
	CÁCERES(10,"Cáceres"),
	CÁDIZ(11,"Cádiz"),
	CANTABRIA(39,"Cantabria"),
	CASTELLÓN(12,"Castellón/Castelló"),
	CIUDADREAL(13,"Ciudad Real"),
	CÓRDOBA(14,"Córdoba"),
	CORUÑA(15,"Coruña, A"),
	CUENCA(16,"Cuenca"),
	GIPUZKOA(20,"Gipuzkoa"),
	GIRONA(17,"Girona"),
	GRANADA(18,"Granada"),
	GUADALAJARA(19,"Guadalajara"),
	HUELVA(21,"Huelva"),
	HUESCA(22,"Huesca"),
	JAÉN(23,"Jaén"),
	LEÓN(24,"León"),
	LLEIDA(25,"Lleida"),
	LUGO(27,"Lugo"),
	MADRID(28,"Madrid"),
	MÁLAGA(29,"Málaga"),
	MURCIA(30,"Murcia"),
	NAVARRA(31,"Navarra"),
	OURENSE(32,"Ourense"),
	PALENCIA(34,"Palencia"),
	PALMAS(35,"Palmas, Las"),
	PONTEVEDRA(36,"Pontevedra"),
	RIOJA(26,"Rioja, La"),
	SALAMANCA(37,"Salamanca"),
	SANTA(38,"Santa Cruz de Tenerife"),
	SEGOVIA(40,"Segovia"),
	SEVILLA(41,"Sevilla"),
	SORIA(42,"Soria"),
	TARRAGONA(43,"Tarragona"),
	TERUEL(44,"Teruel"),
	TOLEDO(45,"Toledo"),
	VALENCIA(46,"Valencia/València"),
	VALLADOLID(47,"Valladolid"),
	ZAMORA(49,"Zamora"),
	ZARAGOZA(50,"Zaragoza"),
	CEUTA(51,"Ceuta"),
	MELILLA(52,"Melilla");
	
	private Integer codigoProvincia;
	private String nombreProvincia;
	
	private Provincias (Integer codigoProvincia, String nombreProvincia) {
		this.codigoProvincia = codigoProvincia;
		this.nombreProvincia = nombreProvincia;
	}
	
	public Integer getCodigoProvincia() {
		return codigoProvincia;
	}
	
	public String getNombreProvincia() {
		return nombreProvincia;
	}
}
