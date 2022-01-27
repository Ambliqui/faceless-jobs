package com.teamfaceless.facelessjobs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.teamfaceless.facelessjobs.dtos.inscripcion.InscripcionOfertaInscritoDto;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.InscripcionOferta;
import com.teamfaceless.facelessjobs.model.InscripcionOfertaPK;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;

public interface IInscriptionService{

	List<InscripcionOferta> findAll();
	Optional<InscripcionOferta> findById(Integer idInscripcionOferta);
	void create(InscripcionOferta inscripcionOferta);
	void delete (InscripcionOferta inscripcionOferta);
	void delete (Integer idInscripcionOferta);
	void modify (InscripcionOferta inscripcionOferta);
	boolean isPresent(InscripcionOferta inscripcionOferta);
	
	/**
	 * @author Mefisto
	 * Busca una inscripcion por la clave compuesta
	 * @param pk Clave compuesta de la Inscripcion
	 * @return Optional<InscripcionOfertaPK> Clave embebida 
	 */
	Optional<InscripcionOfertaPK> findByInscripcionOfertaPK(InscripcionOfertaPK pk);
	
	/**
	 * @author Mefisto
	 * Devuelve un DTO de las inscripciones de una oferta
	 * con datos sesgados del usuario y una lista de habilidades genericas coincidentes con la oferta
	 * @param ofertaEmpleo
	 * @return List<InscripcionOfertaInscritoDto>
	 */
	List<InscripcionOfertaInscritoDto> inscritosOfertaConHabilidades(OfertaEmpleo ofertaEmpleo);
	/**
	 * @author Mefisto
	 * Valida si un candidato puede optar a la oferta de trabajo
	 * Devolvera un boolean si cumple las reglas de negocio
	 * 
	 */
	String validadorInscripcion(InscripcionOferta inscripcionOferta, Candidato candidato); 
}
