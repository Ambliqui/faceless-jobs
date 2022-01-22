package com.teamfaceless.facelessjobs.services;

import java.util.Optional;

import com.teamfaceless.facelessjobs.model.Rol;

public interface IRolService {

	Rol getRoleById(int id);
	Optional<Rol> findByUser(String user);
	
}
