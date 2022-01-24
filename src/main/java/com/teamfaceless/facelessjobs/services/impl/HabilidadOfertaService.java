package com.teamfaceless.facelessjobs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dao.IHabilidadOfertaRepository;
import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadOferta;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.services.IHabilidadOfertaService;

@Service
public class HabilidadOfertaService implements IHabilidadOfertaService{
	
	@Autowired
	private IHabilidadOfertaRepository repository;
	
	@Override
	public List<HabilidadOferta> findAll() {
		return repository.findAll();
	}

	@Override
	public void create(HabilidadOferta habilidadOferta) {
		repository.save(habilidadOferta);		
	}

	@Override
	public Optional<HabilidadOferta> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void modify(HabilidadOferta habilidadOferta) {
		repository.save(habilidadOferta);		
	}

	@Override
	public void delete(Integer idHabilidad) {
		repository.deleteById(idHabilidad);		
	}

	@Override
	public List<HabilidadOferta> findHabilidadesOfertaByOferta(OfertaEmpleo oferta) {
		return repository.findHabilidadesOfertaByOferta(oferta);
	}

	@Override
	public List<Habilidad> findHabilidadesByOfertaEmpleo(OfertaEmpleo oferta) {
		return repository.findHabilidadesByOferta(oferta);
	}
	
	

	
}
