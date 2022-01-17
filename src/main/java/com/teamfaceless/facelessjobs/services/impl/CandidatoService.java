package com.teamfaceless.facelessjobs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dao.ICandidatoRepository;
import com.teamfaceless.facelessjobs.exceptions.EmailExisteException;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.services.ICandidatoService;

@Service
public class CandidatoService implements ICandidatoService {

	@Autowired
	private ICandidatoRepository repository;
	
	@Override
	public List<Candidato> findAll() {
		return repository.findAll();
	}

	@Override
	public void create(Candidato candidato) throws EmailExisteException {
		if(isPresent(candidato)) {
			throw  new EmailExisteException("Email en uso");
		}
		repository.save(candidato);
	}

	@Override
	public List<Candidato> findByNombre(String nombre) {
		return repository.findByNombreCandidato(nombre);
	}

	@Override
	public Optional<Candidato> findById(Integer id) {
		return repository.findById(id);
	}
	
	private boolean isPresent(Candidato candidato) {
		return repository.findByEmail(candidato.getCredencial().getEmail()).isPresent();
	}

}
