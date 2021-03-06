package com.teamfaceless.facelessjobs.services;

import java.util.List;
import java.util.Optional;

import com.teamfaceless.facelessjobs.exceptions.EmailExisteException;
import com.teamfaceless.facelessjobs.model.Candidato;


public interface ICandidatoService {
	
	Candidato findById(Integer id);
	List<Candidato> findAll();
	void create(Candidato candidato) throws EmailExisteException;
	List<Candidato> findByNombre(String nombre);
	//Optional<Candidato> findById(Integer id);
	Optional<Candidato> findByEmail (String email);

	Optional<Candidato> buscarPorId(Integer id);

	void update(Candidato candidato);
	
}
