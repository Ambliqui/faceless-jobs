package com.teamfaceless.facelessjobs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dao.IProvinciaRepository;
import com.teamfaceless.facelessjobs.model.Provincia;
import com.teamfaceless.facelessjobs.services.IProvinciaService;

@Service
public class ProvinciaService implements IProvinciaService{

	@Autowired
	private IProvinciaRepository repository;

	@Override
	public void create(Provincia provincia) {
		if(!isPresent(provincia)) {
			repository.save(provincia);
		}
	}

	@Override
	public Optional<Provincia> findByNombre(String nombre) {
		return repository.findByNombreProvincia(nombre);
	}
	
	private boolean isPresent(Provincia provincia) {
		return repository.findByNombreProvincia(provincia.getNombreProvincia()).isPresent();
	}

	@Override
	public List<Provincia> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Provincia> findById(Integer provinciaId) {
		return repository.findById(provinciaId);
	}
	
}
