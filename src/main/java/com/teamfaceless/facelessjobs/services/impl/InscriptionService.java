package com.teamfaceless.facelessjobs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dao.IInscriptionRepository;
import com.teamfaceless.facelessjobs.model.InscripcionOferta;
import com.teamfaceless.facelessjobs.model.InscripcionOfertaPK;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.services.IInscriptionService;

@Service
public class InscriptionService implements IInscriptionService{

	@Autowired
	IInscriptionRepository repository;

	@Override
	public List<InscripcionOferta> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<InscripcionOferta> findById(Integer idInscripcionOferta) {
		// TODO Auto-generated method stub		
		return repository.findById(idInscripcionOferta);
	}

	@Override
	public void create(InscripcionOferta inscripcionOferta) {
		repository.save(inscripcionOferta);
		
	}

	@Override
	public void delete(InscripcionOferta inscripcionOferta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer idInscripcionOferta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(InscripcionOferta inscripcionOferta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPresent(InscripcionOferta inscripcionOferta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<InscripcionOfertaPK> findByInscripcionOfertaPK(InscripcionOfertaPK pk) {
		// TODO Auto-generated method stub
		return null;
	}

}
