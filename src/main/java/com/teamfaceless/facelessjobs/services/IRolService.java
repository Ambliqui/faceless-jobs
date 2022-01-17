package com.teamfaceless.facelessjobs.services;

import com.teamfaceless.facelessjobs.model.Rol;

public interface IRolService {

	Rol findByNombre(String nombre);
	void create(Rol rol);
	
}
