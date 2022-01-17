package com.teamfaceless.facelessjobs.services;

import java.util.List;
import java.util.Optional;

import com.teamfaceless.facelessjobs.model.Provincia;

public interface IProvinciaService {
	List<Provincia>findAll();
	void create(Provincia provincia);
	Optional<Provincia> findByNombre(String nombre);
	Optional<Provincia> findById(Integer provinciaId);
}
