package com.teamfaceless.facelessjobs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadCandidato;

public interface IHabilidadCandidatoRepository extends JpaRepository<HabilidadCandidato,Integer>{
	
	@Modifying
	@Query("delete from HabilidadCandidato where candidato=:candidato and habilidad=:habilidad")
	void deleteHabilidadCandidato(@Param("candidato") Candidato candidato, @Param("habilidad") Habilidad habilidad);
	
	@Query("FROM HabilidadCandidato WHERE candidato = :thisCandidato AND habilidad = :thisHabilidad")
	HabilidadCandidato findHabilidadCandidatoByCandidatoAndHabilidad(@Param("thisCandidato") Candidato candidato, @Param("thisHabilidad") Habilidad habilidad);
	
	@Query(value="SELECT id_habilidad FROM habilidad "
			+ "where not exists ( "
			+ "	select 1 "
			+ "    from habilidad_candidato "
			+ "    where "
			+ "    habilidad_candidato.habilidad_id_habilidad = habilidad.id_habilidad "
			+ "    and "
			+ "    habilidad_candidato.candidato_id_habilidad = ?1"
			+ ") "
			+ "and "
			+ "habilidad.categoria_habilidad = 1",nativeQuery=true)
	List<Integer> findHabilidadesDurasRestantesByCandidato(@Param("candidato") int idCandidato);
	
	@Query(value="SELECT id_habilidad FROM habilidad "
			+ "where not exists ( "
			+ "	select 1 "
			+ "    from habilidad_candidato "
			+ "    where "
			+ "    habilidad_candidato.habilidad_id_habilidad = habilidad.id_habilidad "
			+ "    and "
			+ "    habilidad_candidato.candidato_id_habilidad = ?1"
			+ ") "
			+ "and "
			+ "habilidad.categoria_habilidad = 0",nativeQuery=true)
	List<Integer> findHabilidadesBlandasRestantesByCandidato(@Param("candidato") int idCandidato);
	
	
	@Query(value="SELECT candidato_id_habilidad,habilidad_id_habilidad,experiencia_candidato,is_demostrable,nota_habilidad_candidato "
			+ "FROM habilidad "
			+ "inner join habilidad_candidato "
			+ "on habilidad.id_habilidad = habilidad_candidato.habilidad_id_habilidad "
			+ "where habilidad_candidato.candidato_id_habilidad = ?1 and habilidad.categoria_habilidad = 1",nativeQuery = true)
	List<HabilidadCandidato>findHabilidadesCandidatoDurasByCandidato(@Param("candidato") int idCandidato);
	
	@Query(value="SELECT candidato_id_habilidad,habilidad_id_habilidad,experiencia_candidato,is_demostrable,nota_habilidad_candidato "
			+ "FROM habilidad "
			+ "inner join habilidad_candidato "
			+ "on habilidad.id_habilidad = habilidad_candidato.habilidad_id_habilidad "
			+ "where habilidad_candidato.candidato_id_habilidad = ?1 and habilidad.categoria_habilidad = 0",nativeQuery = true)
	List<HabilidadCandidato>findHabilidadesCandidatoBlandasByCandidato(@Param("candidato") int idCandidato);
	
	
}
