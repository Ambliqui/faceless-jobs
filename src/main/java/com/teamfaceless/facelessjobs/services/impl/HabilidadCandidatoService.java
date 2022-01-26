package com.teamfaceless.facelessjobs.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamfaceless.facelessjobs.dao.IHabilidadCandidatoRepository;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadCandidato;
import com.teamfaceless.facelessjobs.services.IHabilidadCandidatoService;
import com.teamfaceless.facelessjobs.services.IHabilidadService;

@Service
public class HabilidadCandidatoService implements IHabilidadCandidatoService{

	@Autowired
	private IHabilidadCandidatoRepository repository;
	
	@Autowired
	private IHabilidadService habService;

	@Override
	public void modify(HabilidadCandidato habilidadCandidato) {
		repository.save(habilidadCandidato);		
	}
	
	@Override
	@Transactional
	public void delete(HabilidadCandidato habilidadCandidato) {
		Candidato candidato = habilidadCandidato.getCandidato();
		Habilidad habilidad = habilidadCandidato.getHabilidad();
		repository.deleteHabilidadCandidato(candidato, habilidad);
	}
	
	@Override
	public List<Habilidad> findHabilidadesDurasRestantesByCandidato(Candidato candidato) {
		List<Integer> listaId = repository.findHabilidadesDurasRestantesByCandidato(candidato.getIdCandidato());
		List<Habilidad> listaHabilidades = new ArrayList<>();
		for(Integer id : listaId) {
			Habilidad nuevaHabilidad = habService.findById(id).get();
			listaHabilidades.add(nuevaHabilidad);
		}
		return listaHabilidades;
	}

	@Override
	public List<Habilidad> findHabilidadesBlandasRestantesByCandidato(Candidato candidato) {
		List<Integer> listaId = repository.findHabilidadesBlandasRestantesByCandidato(candidato.getIdCandidato());
		List<Habilidad> listaHabilidades = new ArrayList<>();
		for(Integer id : listaId) {
			Habilidad nuevaHabilidad = habService.findById(id).get();
			listaHabilidades.add(nuevaHabilidad);
		}
		return listaHabilidades;
	}

	@Override
	public List<HabilidadCandidato> findHabilidadesCandidatoDurasByCandidato(Candidato candidato) {
		return repository.findHabilidadesCandidatoDurasByCandidato(candidato.getIdCandidato());
	}

	@Override
	public List<HabilidadCandidato> findHabilidadesCandidatoBlandasByCandidato(Candidato candidato) {
		return repository.findHabilidadesCandidatoBlandasByCandidato(candidato.getIdCandidato());
	}
	
	@Override
	public HabilidadCandidato findHabilidadCandidatoByCandidatoAndHabilidad(Candidato candidato, Habilidad habilidad) {
		return repository.findHabilidadCandidatoByCandidatoAndHabilidad(candidato, habilidad);
	}
	
	
}
