package com.teamfaceless.facelessjobs.services;

import java.util.List;
import java.util.Optional;

import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadOferta;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;

public interface IHabilidadOfertaService {

	List<HabilidadOferta> findAll();
	void create(HabilidadOferta habilidadOferta);
	Optional<HabilidadOferta> findById(Integer id);
	void modify(HabilidadOferta habilidadOferta);
	void delete(Integer idHabilidad);
	//List<HabilidadOferta> findHabilidadOfertaByXXX(@Param("XXX") XXX xxx);
	List<HabilidadOferta> findHabilidadesOfertaByOferta(OfertaEmpleo oferta);
	List<Habilidad> findHabilidadesByOferta(OfertaEmpleo oferta);
	List<Habilidad> findHabilidadesRestantesByOferta(OfertaEmpleo oferta);
}
