package com.teamfaceless.facelessjobs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamfaceless.facelessjobs.model.InscripcionOferta;
import com.teamfaceless.facelessjobs.model.InscripcionOfertaPK;

public interface IInscriptionRepository  extends JpaRepository<InscripcionOferta, Integer>{
	
	InscripcionOferta findByInscripcionOfertaPK(InscripcionOfertaPK primaryKey);

}
