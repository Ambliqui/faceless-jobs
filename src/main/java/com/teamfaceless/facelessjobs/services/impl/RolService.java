package com.teamfaceless.facelessjobs.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dao.IRolRepository;
import com.teamfaceless.facelessjobs.model.Rol;
import com.teamfaceless.facelessjobs.services.IRolService;

@Service
public class RolService implements IRolService {

	@Autowired
	private IRolRepository repository;
	
	@Override
	public Rol findByNombre(String nombre) {
		return repository.findByNombre(nombre).get();
	}

	@Override
	public void create(Rol rol) {
		repository.save(rol);
	}

}
