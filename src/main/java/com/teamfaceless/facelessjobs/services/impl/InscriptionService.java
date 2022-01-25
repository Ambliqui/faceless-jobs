package com.teamfaceless.facelessjobs.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dao.IInscriptionRepository;
import com.teamfaceless.facelessjobs.dtos.inscripcion.InscripcionOfertaInscritoDto;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadCandidato;
import com.teamfaceless.facelessjobs.model.HabilidadOferta;
import com.teamfaceless.facelessjobs.model.InscripcionOferta;
import com.teamfaceless.facelessjobs.model.InscripcionOfertaPK;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.services.IInscriptionService;

@Service
public class InscriptionService implements IInscriptionService{

	@Autowired
	IInscriptionRepository repository;

	@Override
	public List<InscripcionOferta> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<InscripcionOferta> findById(Integer idInscripcionOferta) {
		// TODO Auto-generated method stub		
		return repository.findById(idInscripcionOferta);
	}

	@Override
	public void create(InscripcionOferta inscripcionOferta) {
		repository.save(inscripcionOferta);
		
	}

	@Override
	public void delete(InscripcionOferta inscripcionOferta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer idInscripcionOferta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(InscripcionOferta inscripcionOferta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPresent(InscripcionOferta inscripcionOferta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<InscripcionOfertaPK> findByInscripcionOfertaPK(InscripcionOfertaPK pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InscripcionOfertaInscritoDto> inscritosOfertaConHabilidades(OfertaEmpleo ofertaEmpleo) {
		
		List<InscripcionOfertaInscritoDto> candidatosInscritos = new ArrayList<>();
		
		//Obtenemos las inscripciones de la oferta de empleo
		List<InscripcionOferta> inscripciones = ofertaEmpleo.getInscripcionOfertaList();
		List<HabilidadOferta> habilidadesOferta = ofertaEmpleo.getHabilidadOfertaList();
		
		for (InscripcionOferta inscripcion : inscripciones) {
			//Creamos un nuevo objeto de la vista
			InscripcionOfertaInscritoDto inscritoDto = new InscripcionOfertaInscritoDto();
			
			//Guardamos las propiedades del candidato inscrito
			inscritoDto.setIdCandidato(inscripcion.getCandidato().getIdCandidato());
//			inscritoDto.setProvincia(inscripcion.getCandidato().getProvinciaCandidato().getNombreProvincia());
			inscritoDto.setLocalidad(inscripcion.getCandidato().getLocalidadCandidato());
			
			//Recogemos las habilidades del candidato
			List<HabilidadCandidato> habilidadesCandidato = inscripcion.getCandidato().getHabilidadCandidatoList();
			
			//Lista para rellenar en orden las habilidades
			List<Habilidad> habilidadesCoincidentes = new ArrayList<>();
			
			//Bucle para las habilidades requeridas de la oferta
			for (HabilidadOferta habOfer : habilidadesOferta) {
				
				//Comprobamos cada una de las habilidades de la oferta con todas las habilidades del candidato
				for (HabilidadCandidato habCandidato : habilidadesCandidato) {
					if (habCandidato.getHabilidad().equals(habOfer.getHabilidad())) {
						habilidadesCoincidentes.add(habCandidato.getHabilidad());
						break;
					}
				}
				inscritoDto.setHabilidades(habilidadesCoincidentes);
			}
			candidatosInscritos.add(inscritoDto);
		}
		return candidatosInscritos;
	}

}
