package com.teamfaceless.facelessjobs.services.impl;

import java.util.List;
import java.util.Optional;

import com.teamfaceless.facelessjobs.dao.ICandidatoRepository;
import com.teamfaceless.facelessjobs.dao.IRolRepository;
import com.teamfaceless.facelessjobs.exceptions.EmailExisteException;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Rol;
import com.teamfaceless.facelessjobs.services.ICandidatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService implements ICandidatoService {

	@Autowired
	private ICandidatoRepository repository;

	@Autowired
	private IRolRepository rolRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<Candidato> findAll() {
		return repository.findAll();
	}

	@Override
	public void create(Candidato candidato) throws EmailExisteException {
		if(isPresent(candidato)) {
			throw  new EmailExisteException("Email en uso");
		}

		
		Rol rol = rolRepository.findById(1).get();
		candidato.getCredencial().addRole(rol);

		String password = candidato.getCredencial().getPass();
		password = passwordEncoder.encode(password);
		candidato.getCredencial().setPass(password);
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

	@Override
	public Optional<Candidato> findByEmail(String email) {
		return repository.findByEmail(email);
	}

}
