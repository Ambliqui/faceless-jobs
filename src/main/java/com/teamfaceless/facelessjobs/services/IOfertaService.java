package com.teamfaceless.facelessjobs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;


public interface IOfertaService {
	List<OfertaEmpleo> findAll();

	Optional<OfertaEmpleo> findById(Integer id);

	void create(OfertaEmpleo oferta);

	void delete(OfertaEmpleo oferta);

	void delete(Integer idOferta);

	void modify(OfertaEmpleo OfertaEmpleo);
	
	List<OfertaEmpleo> findOfertaByEmpresa(
			  @Param("idEmpresa") Integer idEmpresa);
	
	Page<OfertaEmpleo> findAllPageable(Pageable pageable);
	
	List<OfertaEmpleo> findOfertaByidCandidato(Integer idCandidato);
	
	Page<OfertaEmpleo> findByTituloAndDescripcion(
			String titulo, String descrpcion,String provincia,
			String sector,Integer salarioMinimo, Integer salarioMaximo,Integer nPagina,Integer nElementos);
	
	OfertaEmpleo save(OfertaEmpleo oferta);
	
	void cerrarOferta(Integer idOferta);
	
	void cerrarOferta(OfertaEmpleo oferta);
}
