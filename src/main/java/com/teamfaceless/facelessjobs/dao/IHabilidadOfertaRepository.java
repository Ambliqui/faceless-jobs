package com.teamfaceless.facelessjobs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadOferta;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;

public interface IHabilidadOfertaRepository extends JpaRepository<HabilidadOferta,Integer>{

	@Query("FROM HabilidadOferta WHERE ofertaEmpleo = :oferta")
	List<HabilidadOferta> findHabilidadesOfertaByOferta(@Param("oferta") OfertaEmpleo oferta);
	
	@Query("SELECT habilidad FROM HabilidadOferta WHERE oferta_id_habilidad = :oferta")
	List<Habilidad> findHabilidadesByOferta(@Param("oferta") OfertaEmpleo oferta);
//	
//	List<HabilidadOferta> findByOfertaEmpleo(OfertaEmpleo oferta);
}
