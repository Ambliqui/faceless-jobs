package com.teamfaceless.facelessjobs.dtos.candidato.mapper;

import com.teamfaceless.facelessjobs.dtos.candidato.CandidatoModifyDto;
import com.teamfaceless.facelessjobs.dtos.candidato.CandidatoRegistroDto;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Credencial;
import com.teamfaceless.facelessjobs.services.IProvinciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatoMapper implements ICandidatoMapper {

	@Autowired
	private IProvinciaService provinciaService;

	@Override
	public Candidato candidatoRegistroDtoToCandidato(CandidatoRegistroDto candidatoRegistroDto) {

		return Candidato.builder()
				.nombreCandidato(candidatoRegistroDto.getNombreCandidato().trim())
				.apellidosCandidato(candidatoRegistroDto.getApellidosCandidato().trim())
				.credencial(Credencial.builder()
						.email(candidatoRegistroDto.getEmailCandidato().trim())
						.pass(candidatoRegistroDto.getPassCandidato().trim())
						.enable(candidatoRegistroDto.getEnable())
						.roles(candidatoRegistroDto.getRoles())
						.build())
				.fechaNacimientoCandidato(candidatoRegistroDto.getFechaNacimientoCandidato())
				.telefonoCandidato(candidatoRegistroDto.getTelefonoCandidato().trim())
				.provinciaCandidato(provinciaService.findById(candidatoRegistroDto.getProvinciaCandidato()).get())
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
				.provinciaCandidato(candidato.getProvinciaCandidato().getIdProvincia())
				.build();
	}

	@Override
	public Candidato candidatoModifyDtoToCandidato(CandidatoModifyDto candidatoModifyDto) {

		return Candidato.builder()
				.idCandidato(candidatoModifyDto.getIdCandidato())
				.nombreCandidato(candidatoModifyDto.getNombreCandidato())
				.apellidosCandidato(candidatoModifyDto.getApellidosCandidato())
				.fechaNacimientoCandidato(candidatoModifyDto.getFechaNacimientoCandidato())
				.telefonoCandidato(candidatoModifyDto.getTelefonoCandidato())
				.credencial(candidatoModifyDto.getCredencial())
				.build();

	}

}
