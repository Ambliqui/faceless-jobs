package com.teamfaceless.facelessjobs.dtos.candidato.mapper;

import com.teamfaceless.facelessjobs.dtos.candidato.CandidatoRegistroDto;
import com.teamfaceless.facelessjobs.model.Candidato;

public interface ICandidatoMapper {

	Candidato candidatoRegistroDtoToCandidato(CandidatoRegistroDto candidatoRegistroDto);
	
	CandidatoRegistroDto candidatoToCandidatoRegistroDto(Candidato candidato);
	
}
