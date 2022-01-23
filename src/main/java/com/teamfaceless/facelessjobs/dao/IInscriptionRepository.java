package com.teamfaceless.facelessjobs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamfaceless.facelessjobs.model.InscripcionOferta;
import com.teamfaceless.facelessjobs.model.InscripcionOfertaPK;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;

public interface IInscriptionRepository  extends JpaRepository<InscripcionOferta, Integer>{
	
	InscripcionOferta findByInscripcionOfertaPK(InscripcionOfertaPK primaryKey);
	
	List<InscripcionOferta> findByOfertaEmpleo(OfertaEmpleo ofertaEmpleo);

}
