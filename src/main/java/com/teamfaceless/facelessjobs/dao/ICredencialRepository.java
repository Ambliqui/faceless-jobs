package com.teamfaceless.facelessjobs.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamfaceless.facelessjobs.model.Credencial;

public interface ICredencialRepository extends JpaRepository<Credencial, Integer> {

	Optional<Credencial> findByEmail(String email);
	
}
