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
	List<Habilidad> generalizacionHabilidadesCandidato (List<HabilidadCandidato> habilidadesCandidato);
	List<HabilidadCandidato> especializacionHabilidadesCandidato (List<Habilidad> habilidades, Candidato candidato);
}
