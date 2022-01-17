package com.teamfaceless.facelessjobs.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamfaceless.facelessjobs.model.Rol;

public interface IRolRepository extends JpaRepository<Rol, Integer> {

	Optional<Rol> findByNombre(String nombre);
	
}
