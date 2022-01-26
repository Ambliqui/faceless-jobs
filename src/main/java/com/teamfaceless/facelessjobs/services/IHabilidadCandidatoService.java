package com.teamfaceless.facelessjobs.services;

import java.util.List;

import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadCandidato;
import com.teamfaceless.facelessjobs.model.HabilidadOferta;

public interface IHabilidadCandidatoService {
	void modify(HabilidadCandidato habilidadCandidato);
	void delete(HabilidadCandidato habilidadOferta);
	HabilidadCandidato findHabilidadCandidatoByCandidatoAndHabilidad(Candidato candidato, Habilidad habilidad);
	List<HabilidadCandidato> findHabilidadesCandidatoDurasByCandidato(Candidato candidato);
	List<HabilidadCandidato> findHabilidadesCandidatoBlandasByCandidato(Candidato candidato);
	List<Habilidad> findHabilidadesDurasRestantesByCandidato(Candidato candidato);
	List<Habilidad> findHabilidadesBlandasRestantesByCandidato(Candidato candidato);
	
	/**
	 * @author Mefisto
	 * Devuelve una lista de Habilidad a partir de las habilidades del candidato
	 * @param habilidadesCandidato
	 * @return Lista Habilidad
	 */
	
	List<Habilidad> generalizacionHabilidadesCandidato (List<HabilidadCandidato> habilidadesCandidato);
	/**
	 * Devuelve una lista de HabilidadCandidato a partir de una lista de Habilidad genérica
	 * @param habilidades
	 * @param candidato
	 * @return Lista de HabilidadCandidato 
	 */
	List<HabilidadCandidato> especializacionHabilidadesCandidatoCoincidentes (List<Habilidad> habilidades, Candidato candidato);
	
	/**
	 * @author Mefisto
	 * Devuelve una lista de HabilidadCandidato a partir de una lista de Habilidad genérica
	 * Si la habilidad no existe se da mete una de relleno para realizar calculos
	 * @param habilidades
	 * @param candidato
	 * @return Lista de HabilidadCandidato 
	 */
	List<HabilidadCandidato> especializacionHabilidadesCandidatoRellenos(List<Habilidad> habilidades, Candidato candidato);

}
