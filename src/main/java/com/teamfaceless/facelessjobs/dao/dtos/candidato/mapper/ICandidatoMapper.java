package com.teamfaceless.facelessjobs.dao.dtos.candidato.mapper;

import com.teamfaceless.facelessjobs.dao.dtos.candidato.CandidatoRegistroDto;
import com.teamfaceless.facelessjobs.model.Candidato;

public interface ICandidatoMapper {

	Candidato candidatoRegistroDtoToCandidato(CandidatoRegistroDto candidatoRegistroDto);
	
	CandidatoRegistroDto candidatoToCandidatoRegistroDto(Candidato candidato);
	
}
