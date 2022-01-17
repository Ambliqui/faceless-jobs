package com.teamfaceless.facelessjobs.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamfaceless.facelessjobs.model.Provincia;

public interface IProvinciaRepository extends JpaRepository<Provincia, Integer> {

	Optional<Provincia> findByNombreProvincia(String nombreProvincia);
	
}
