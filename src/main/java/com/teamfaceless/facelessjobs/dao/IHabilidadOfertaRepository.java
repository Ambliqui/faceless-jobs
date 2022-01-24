package com.teamfaceless.facelessjobs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	@Query("FROM HabilidadOferta WHERE ofertaEmpleo = :thisOferta AND habilidad = :thisHabilidad")
	HabilidadOferta findHabilidadOfertaByOfertaAndHabilidad(@Param("thisOferta") OfertaEmpleo oferta, @Param("thisHabilidad") Habilidad habilidad);
	
	@Modifying
	@Query("delete from HabilidadOferta where ofertaEmpleo=:oferta and habilidad=:habilidad")
	void deleteHabilidadOferta(@Param("oferta") OfertaEmpleo oferta, @Param("habilidad") Habilidad habilidad);
	
	/*
	 * SELECT id_habilidad FROM habilidad
inner join habilidad_oferta
on habilidad.id_habilidad = habilidad_oferta.habilidad_id_habilidad
where habilidad_oferta.oferta_id_habilidad = 18 and habilidad.categoria_habilidad = 0 
;
	 * 
	 * 	
	 */
	
//	
//	List<HabilidadOferta> findByOfertaEmpleo(OfertaEmpleo oferta);
}
