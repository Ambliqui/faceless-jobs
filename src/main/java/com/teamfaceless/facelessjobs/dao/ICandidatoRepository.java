package com.teamfaceless.facelessjobs.dao;

import java.util.List;
import java.util.Optional;

import com.teamfaceless.facelessjobs.model.Candidato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICandidatoRepository extends JpaRepository<Candidato, Integer> {

	List<Candidato> findByNombreCandidato(String nombreCandidato);

	@Query("FROM Candidato WHERE credencial.email = :emailCandidato")
	Optional<Candidato> findByEmail(String emailCandidato);

	// query wich return a candidato with find by id
	@Query("FROM Candidato WHERE credencial.id = :idCandidato")
	Optional<Candidato> buscarPorId(Integer idCandidato);
}
