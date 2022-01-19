package com.teamfaceless.facelessjobs.dao.dtos.candidato.mapper;

import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dao.dtos.candidato.CandidatoRegistroDto;
import com.teamfaceless.facelessjobs.enums.Provincias;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Credencial;

@Service
public class CandidatoMapper implements ICandidatoMapper {

	@Override
	public Candidato candidatoRegistroDtoToCandidato(CandidatoRegistroDto candidatoRegistroDto) {

		return Candidato.builder()
				.nombreCandidato(candidatoRegistroDto.getNombreCandidato().trim())
				.apellidosCandidato(candidatoRegistroDto.getApellidosCandidato().trim())
				.credencial(Credencial.builder()
						.email(candidatoRegistroDto.getEmailCandidato().trim())
						.pass(candidatoRegistroDto.getPassCandidato().trim())
						.build())
				.provinciaCandidato(candidatoRegistroDto.getProvinciaCandidato())
				.fechaNacimientoCandidato(candidatoRegistroDto.getFechaNacimientoCandidato())
				.telefonoCandidato(candidatoRegistroDto.getTelefonoCandidato().trim())		
				.build();
	}

	@Override
	public CandidatoRegistroDto candidatoToCandidatoRegistroDto(Candidato candidato) {
		
		return CandidatoRegistroDto.builder()
				.nombreCandidato(candidato.getNombreCandidato())
				.apellidosCandidato(candidato.getApellidosCandidato())
				.emailCandidato(candidato.getCredencial().getEmail())
				.fechaNacimientoCandidato(candidato.getFechaNacimientoCandidato())
				.telefonoCandidato(candidato.getTelefonoCandidato())
				.build();
	}

}
