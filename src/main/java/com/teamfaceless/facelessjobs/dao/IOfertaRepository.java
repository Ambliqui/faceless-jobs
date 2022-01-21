package com.teamfaceless.facelessjobs.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;

public interface IOfertaRepository extends JpaRepository<OfertaEmpleo,Integer> {

	@Query("SELECT o FROM OfertaEmpleo o WHERE o.empresa.idEmpresa = :idEmpresa")
	List<OfertaEmpleo> findOfertaByidEmpresa(Integer idEmpresa);
	
	
}
