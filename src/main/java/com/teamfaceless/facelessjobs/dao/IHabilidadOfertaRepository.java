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

//	@Query("FROM HabilidadOferta WHERE ofertaEmpleo = :oferta")
//	List<HabilidadOferta> findHabilidadesOfertaByOferta(@Param("oferta") OfertaEmpleo oferta);
	
//	@Query("SELECT habilidad FROM HabilidadOferta WHERE oferta_id_habilidad = :oferta")
//	List<Habilidad> findHabilidadesByOferta(@Param("oferta") OfertaEmpleo oferta);
	
	@Query("FROM HabilidadOferta WHERE ofertaEmpleo = :thisOferta AND habilidad = :thisHabilidad")
	HabilidadOferta findHabilidadOfertaByOfertaAndHabilidad(@Param("thisOferta") OfertaEmpleo oferta, @Param("thisHabilidad") Habilidad habilidad);
	
	@Modifying
	@Query("delete from HabilidadOferta where ofertaEmpleo=:oferta and habilidad=:habilidad")
	void deleteHabilidadOferta(@Param("oferta") OfertaEmpleo oferta, @Param("habilidad") Habilidad habilidad);
	
	@Query(value="SELECT habilidad_id_habilidad,oferta_id_habilidad,experiencia_oferta,baremo_habilidad_oferta,is_obligatorio_habilidad_oferta FROM habilidad "
			+ "inner join habilidad_oferta "
			+ "on habilidad.id_habilidad = habilidad_oferta.habilidad_id_habilidad "
			+ "where habilidad_oferta.oferta_id_habilidad = ?1 and habilidad.categoria_habilidad = 1 "
			+ "order by habilidad_oferta.baremo_habilidad_oferta desc",nativeQuery = true)
	List<HabilidadOferta> findHabilidadesOfertaDurasByOferta(@Param("oferta") int idOferta);
	
	@Query(value="SELECT habilidad_id_habilidad,oferta_id_habilidad,experiencia_oferta,baremo_habilidad_oferta,is_obligatorio_habilidad_oferta FROM habilidad "
			+ "inner join habilidad_oferta "
			+ "on habilidad.id_habilidad = habilidad_oferta.habilidad_id_habilidad "
			+ "where habilidad_oferta.oferta_id_habilidad = ?1 and habilidad.categoria_habilidad = 0 "
			+ "order by habilidad_oferta.baremo_habilidad_oferta desc",nativeQuery = true)
	List<HabilidadOferta> findHabilidadesOfertaBlandasByOferta(@Param("oferta") int idOferta);
	
	@Query(value="SELECT id_habilidad FROM habilidad "
			+ "where not exists ( "
			+ "	select 1 "
			+ "    from habilidad_oferta "
			+ "    where "
			+ "    habilidad_oferta.habilidad_id_habilidad = habilidad.id_habilidad "
			+ "    and "
			+ "    habilidad_oferta.oferta_id_habilidad = ?1"
			+ ") "
			+ "and "
			+ "habilidad.categoria_habilidad = 1",nativeQuery=true)
	List<Integer> findHabilidadesDurasRestantesByOferta(@Param("oferta") int idOferta);
	
//	@Query("FROM Habilidad where not exists "
//			+ "(select 1 FROM HabilidadOferta WHERE habilidad_oferta.habilidad_id_habilidad = habilidad.id_habilidad AND ofertaEmpleo := oferta)"
//			+ " and categoria = 1")
//	List<Habilidad> findHabilidadesDurasRestantesByOferta(@Param("oferta") OfertaEmpleo oferta);
//	
	@Query(value="SELECT id_habilidad FROM habilidad "
			+ "where not exists ( "
			+ "	select 1 "
			+ "    from habilidad_oferta "
			+ "    where "
			+ "    habilidad_oferta.habilidad_id_habilidad = habilidad.id_habilidad "
			+ "    and "
			+ "    habilidad_oferta.oferta_id_habilidad = ?1"
			+ ") "
			+ "and "
			+ "habilidad.categoria_habilidad = 0",nativeQuery=true)
	List<Integer> findHabilidadesBlandasRestantesByOferta(@Param("oferta") int idOferta);
	
//	@Query("FROM Habilidad where not exists "
//			+ "(select 1 FROM HabilidadOferta WHERE habilidad_oferta.habilidad_id_habilidad = habilidad.id_habilidad AND ofertaEmpleo := oferta)"
//			+ " and categoria = 1")
//	List<Habilidad> findHabilidadesBlandasRestantesByOferta(@Param("oferta") OfertaEmpleo oferta);
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
