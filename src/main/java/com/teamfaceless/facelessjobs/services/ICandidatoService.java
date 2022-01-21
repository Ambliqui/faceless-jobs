package com.teamfaceless.facelessjobs.services;

import java.util.List;
import java.util.Optional;

import com.teamfaceless.facelessjobs.exceptions.EmailExisteException;
import com.teamfaceless.facelessjobs.model.Candidato;


public interface ICandidatoService {

	List<Candidato> findAll();
	void create(Candidato candidato) throws EmailExisteException;
	List<Candidato> findByNombre(String nombre);
	Optional<Candidato> findById(Integer id);
	Optional<Candidato> findByEmail (String email);
	
}
