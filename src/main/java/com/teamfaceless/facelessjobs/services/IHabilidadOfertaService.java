package com.teamfaceless.facelessjobs.services;

import java.util.List;

import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadCandidato;
import com.teamfaceless.facelessjobs.model.HabilidadOferta;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;

public interface IHabilidadOfertaService {

//	List<HabilidadOferta> findAll();
//	void create(HabilidadOferta habilidadOferta);
//	Optional<HabilidadOferta> findById(Integer id);
	void modify(HabilidadOferta habilidadOferta);
	void delete(HabilidadOferta habilidadOferta);
	//List<HabilidadOferta> findHabilidadOfertaByXXX(@Param("XXX") XXX xxx);
//	List<HabilidadOferta> findHabilidadesOfertaByOferta(OfertaEmpleo oferta);
//	List<Habilidad> findHabilidadesByOferta(OfertaEmpleo oferta);
//	List<Habilidad> findHabilidadesRestantesByOferta(OfertaEmpleo oferta);
	HabilidadOferta findHabilidadOfertaByOfertaAndHabilidad(OfertaEmpleo oferta, Habilidad habilidad);
	List<HabilidadOferta> findHabilidadesOfertaDurasByOferta(OfertaEmpleo oferta);
	List<HabilidadOferta> findHabilidadesOfertaBlandasByOferta(OfertaEmpleo oferta);
	List<Habilidad> findHabilidadesDurasRestantesByOferta(OfertaEmpleo oferta);
	List<Habilidad> findHabilidadesBlandasRestantesByOferta(OfertaEmpleo oferta);
	List<Habilidad> generalizacionHabilidadesOferta (List<HabilidadOferta> habilidadesOferta);
	List<HabilidadOferta> especializacionHabilidadesOferta (List<Habilidad> habilidades, OfertaEmpleo OfertaEmpleo);
	List<HabilidadOferta> habilidadesDurasRequeridas (List<HabilidadOferta> habilidadOfertas);
	List<HabilidadOferta> habilidadesDurasNoRequeridas (List<HabilidadOferta> habilidadOfertas);
	List<HabilidadOferta> habilidadesBlandasRequeridas (List<HabilidadOferta> habilidadOfertas);
}
