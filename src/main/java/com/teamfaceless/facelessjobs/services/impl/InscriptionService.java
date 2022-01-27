package com.teamfaceless.facelessjobs.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.teamfaceless.facelessjobs.services.IHabilidadOfertaService;
import com.teamfaceless.facelessjobs.services.IInscriptionService;
import com.teamfaceless.facelessjobs.validations.IValidations;

@Service
public class InscriptionService implements IInscriptionService{

	@Autowired
	IInscriptionRepository repository;

	@Autowired
	private IHabilidadCandidatoService habCandidatoService;
	
	@Autowired
	private IHabilidadOfertaService habOfeService;
	
//	@Autowired
//	private IHabilidadService habService;
	
	@Autowired
	private IValidations validations;
	
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
	public InscripcionOferta save(InscripcionOferta inscripcion) {
		InscripcionOferta resultado = repository.save(inscripcion);
		return resultado;
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
			//Se calcula la afinidad
			inscritoDto.setAfinidad(calcularAfinidadCandidato(inscripcion));
			//Se añade el DTO a la lista
			candidatosInscritos.add(inscritoDto);
		}		
		return candidatosInscritos;
	}

	@Override
	public Map<String, String> validadorInscripcion(OfertaEmpleo ofertaEmpleo, Candidato candidato) {
		
		Map<String, String> mapaErrores = new HashMap<>();
		validations.inscripcionRequisitosNoCoincidentes(ofertaEmpleo, candidato)
			.ifPresent((error) -> mapaErrores.put("ErrorSinRequisitos", error.getMessage()));
		validations.inscripcionExistente(ofertaEmpleo, candidato)
			.ifPresent((error) -> mapaErrores.put("ErrorYaInscrito", error.getMessage()));
		
		return mapaErrores;
	}

	public Integer calcularAfinidadCandidato(InscripcionOferta inscripcion) {
		
		//Se calcula el apartado de Habilidades Duras Y Requeridas
	    
	    List<HabilidadOferta> habilidadOfertaDuraReqList = habOfeService.habilidadesDurasRequeridas(inscripcion.getOfertaEmpleo().getHabilidadOfertaList());
	    List<HabilidadCandidato> habilidadCandidatoDuraReqList = habCandidatoService.especializacionHabilidadesCandidatoRellenos(habOfeService.generalizacionHabilidadesOferta(habilidadOfertaDuraReqList),inscripcion.getCandidato());
	    double n1 = multiplicacionCartesiana(habilidadOfertaDuraReqList, habilidadCandidatoDuraReqList);		
		//Se calcula el apartado de Habilidades Duras Y Valorables
		
		List<HabilidadOferta> habilidadOfertaDuraValList = habOfeService.habilidadesDurasNoRequeridas(inscripcion.getOfertaEmpleo().getHabilidadOfertaList());
	    List<HabilidadCandidato> habilidadCandidatoDuraValList = habCandidatoService.especializacionHabilidadesCandidatoRellenos(habOfeService.generalizacionHabilidadesOferta(habilidadOfertaDuraValList),inscripcion.getCandidato());
	    double n2 = multiplicacionCartesiana(habilidadOfertaDuraValList, habilidadCandidatoDuraValList);
		//Se calcula el apartado de Habilidades Blandas
		
		List<HabilidadOferta> habilidadOfertaBlandaList = habOfeService.habilidadesBlandasRequeridas(inscripcion.getOfertaEmpleo().getHabilidadOfertaList());
	    List<HabilidadCandidato> habilidadCandidatoBlandaList = habCandidatoService.especializacionHabilidadesCandidatoRellenos(habOfeService.generalizacionHabilidadesOferta(habilidadOfertaBlandaList),inscripcion.getCandidato());
	    double n3 = multiplicacionCartesiana(habilidadOfertaBlandaList, habilidadCandidatoBlandaList);
		
		//Se calcula la nota final uniendo las 3
		Integer nFinal=(int)(n1/2+n2/5+n3*(3d/10d));
		
		return nFinal;
	}
	
	private double multiplicacionCartesiana(List<HabilidadOferta> ofertaList,List<HabilidadCandidato> candidatoList) {
		double numerador = 0;
		double denominador = 0;
		int l = ofertaList.size();
		if(l==0) {
			return 100;
		}
		for(int i=0;i<l;i++) {
			numerador += ofertaList.get(i).getBaremo()*candidatoList.get(i).getNotaHabilidadCandidato();
			denominador += ofertaList.get(i).getBaremo();
		}
		return (numerador/denominador);
	}
}
