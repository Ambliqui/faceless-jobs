package com.teamfaceless.facelessjobs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.teamfaceless.facelessjobs.model.InscripcionOferta;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;

public interface IInscriptionService{

	List<InscripcionOferta> findAll();
	Optional<InscripcionOferta> findById(Integer idInscripcionOferta);
	void create(InscripcionOferta inscripcionOferta);
	void delete (InscripcionOferta inscripcionOferta);
	void delete (Integer idInscripcionOferta);
	void modify (InscripcionOferta inscripcionOferta);
	boolean isPresent(InscripcionOferta inscripcionOferta);
}
