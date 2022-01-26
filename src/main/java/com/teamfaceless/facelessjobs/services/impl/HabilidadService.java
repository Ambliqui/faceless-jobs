package com.teamfaceless.facelessjobs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dao.IHabilidadRepository;
import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.services.IHabilidadService;

@Service
public class HabilidadService implements IHabilidadService{

	@Autowired
	private IHabilidadRepository repository;
	
	@Override
	public List<Habilidad> findAll() {
		return repository.findAll();
	}
	
	@Override
	public List<Habilidad> findHabilidadesBlandas() {
		return repository.findHabilidadesBlandas();
	}

	@Override
	public List<Habilidad> findHabilidadesDuras() {
		return repository.findHabilidadesDuras();
	}
	
	@Override
	public void create(Habilidad habilidad){
		repository.save(habilidad);
	}

	@Override
	public Optional<Habilidad> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void modify(Habilidad habilidad) {
		repository.save(habilidad);		
	}
	
	@Override
	public void delete(Integer idHabilidad) {
		repository.deleteById(idHabilidad);
	}	
}
