package com.teamfaceless.facelessjobs.services;

import java.util.List;
import java.util.Optional;

import com.teamfaceless.facelessjobs.model.Habilidad;

public interface IHabilidadService {

	List<Habilidad> findAll();
	void create(Habilidad habilidad);
	Optional<Habilidad> findById(Integer id);
	void modify(Habilidad habilidad);
	void delete(Integer idHabilidad);
	List<Habilidad> findHabilidadesBlandas();
	List<Habilidad> findHabilidadesDuras();
}
