package com.teamfaceless.facelessjobs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.teamfaceless.facelessjobs.enums.Categoria;
import com.teamfaceless.facelessjobs.model.Habilidad;

public interface IHabilidadService {

	List<Habilidad> findAll();
	void create(Habilidad habilidad);
	Optional<Habilidad> findById(Integer id);
	void modify(Habilidad habilidad);
	void delete(Integer idHabilidad);
	//Optional<CategoriaHabilidad> findByCategoria(String categoria);
	List<Habilidad> findHabilidadByCategoria(@Param("categoria") Categoria categoria);
}
