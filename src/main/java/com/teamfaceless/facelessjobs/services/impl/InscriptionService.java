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
import com.teamfaceless.facelessjobs.services.IHabilidadCandidatoService;
import com.teamfaceless.facelessjobs.services.IInscriptionService;

@Service
public class InscriptionService implements IInscriptionService{

	@Autowired
	IInscriptionRepository repository;

	@Autowired
	private IHabilidadCandidatoService habCandidatoService;
	
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
		
		//Lista a devolver
		List<InscripcionOfertaInscritoDto> candidatosInscritos = new ArrayList<>();
		
		//Habilidades requeridas de la Oferta
		List<HabilidadOferta> habilidadesOferta = ofertaEmpleo.getHabilidadOfertaList();
		
		//Desmonto las habilidades de la oferta a habilidades simples
		List<Habilidad> habilidadesRequeridas = new ArrayList<>();
		for (HabilidadOferta habOfer : habilidadesOferta) {
			habilidadesRequeridas.add(habOfer.getHabilidad());
		}
		
		//Por cada inscripcion compruebo las habilidades de los candidatos
		List<InscripcionOferta> inscripciones = ofertaEmpleo.getInscripcionOfertaList();
		for (InscripcionOferta inscripcion : inscripciones) {
			
			InscripcionOfertaInscritoDto inscritoDto = new InscripcionOfertaInscritoDto();
			//Guardamos las propiedades del candidato inscrito
			inscritoDto.setIdCandidato(inscripcion.getCandidato().getIdCandidato());
			inscritoDto.setProvincia(inscripcion.getCandidato().getProvinciaCandidato().getNombreProvincia());
//			inscritoDto.setLocalidad(inscripcion.getCandidato().getLocalidadCandidato());			
			
			List<HabilidadCandidato> habilidadesCandidato = inscripcion.getCandidato().getHabilidadCandidatoList();
			//Desmonto en habilidades simples las habilidades del candidato
			List<Habilidad> habilidadCandidatoSimple = new ArrayList<>();
			for (HabilidadCandidato habCand  : habilidadesCandidato) {
				habilidadCandidatoSimple.add(habCand.getHabilidad());
			}
			
			List<Habilidad> matchHabilidad = new ArrayList<>();
			//Bucle de habilidades Requeridas
			for (Habilidad habReq : habilidadesRequeridas) {
				if(habilidadCandidatoSimple.contains(habReq)) {
					matchHabilidad.add(habReq);
				}else {
					Habilidad relleno = new Habilidad();
					relleno.setNombreHabilidad("No");
					matchHabilidad.add(relleno);
				}
			}
			List<HabilidadCandidato> habilidadesEncontradas = habCandidatoService.especializacionHabilidadesCandidatoRellenos(matchHabilidad, inscripcion.getCandidato());
			inscritoDto.setHabilidades(habilidadesEncontradas);
			candidatosInscritos.add(inscritoDto);
		}
		
		return candidatosInscritos;
	}

	@Override
	public String validadorInscripcion(InscripcionOferta inscripcionOferta, Candidato candidato) {
		// TODO Auto-generated method stub
		return null;
	}

}
