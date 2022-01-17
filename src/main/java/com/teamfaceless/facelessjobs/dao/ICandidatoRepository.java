package com.teamfaceless.facelessjobs.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teamfaceless.facelessjobs.model.Candidato;

public interface ICandidatoRepository extends JpaRepository<Candidato, Integer> {

	List<Candidato> findByNombreCandidato(String nombreCandidato);
	
	@Query("FROM Candidato WHERE credencial.email = :emailCandidato")
	Optional<Candidato> findByEmail(String emailCandidato);
}
