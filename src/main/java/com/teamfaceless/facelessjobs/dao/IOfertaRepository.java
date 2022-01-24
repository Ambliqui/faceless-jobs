package com.teamfaceless.facelessjobs.dao;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;

public interface IOfertaRepository extends JpaRepository<OfertaEmpleo,Integer> {

	@Query("SELECT o FROM OfertaEmpleo o WHERE o.empresa.idEmpresa = :idEmpresa")
	List<OfertaEmpleo> findOfertaByidEmpresa(Integer idEmpresa);
	@Query(value = "SELECT oferta_empleo.*"
			+ " FROM oferta_empleo"
			+ " INNER JOIN inscripcion_oferta"
			+ " ON oferta_empleo.id_oferta_empleo = inscripcion_oferta.oferta_id_inscripcion"
			+ " WHERE inscripcion_oferta.candidato_id_inscripcion = ?1",
			nativeQuery = true)	
	List<OfertaEmpleo> findOfertaByidCandidato(Integer idCandidato);
	

	@Query("SELECT o FROM OfertaEmpleo o WHERE "
			+ "(?1 is null or lower(o.tituloOferta) like concat('%',?1,'%')) and "
			+ "(?2 is null or lower(o.descripcionOferta) like lower(concat('%',?2,'%'))) and "
			+ "(?3 is null or lower(o.provinciaOferta.nombreProvincia) like lower(concat('%',?3,'%'))) and "
			+ "(?4 is null or lower(o.sectorOferta.nombreSectorLaboral) like lower(concat('%',?4,'%'))) and "
			+ "(?5 is null or o.salarioOferta > ?5) and "
			+ "(?6 is null or o.salarioOferta < ?6) ")
	List<OfertaEmpleo> findByTituloAndDescripcion(
			String titulo, String descrpcion,String provincia,String sector,
			Integer salarioMinimo, Integer salarioMaximo, Pageable pageable); 
	
}
