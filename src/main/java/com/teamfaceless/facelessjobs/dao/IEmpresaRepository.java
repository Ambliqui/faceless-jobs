package com.teamfaceless.facelessjobs.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;

public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {

	@Query("FROM Empresa WHERE credencial.email = :email")
	Optional<Empresa> findByEmailEmpresa(String email);
	
	@Query("SELECT e FROM Empresa e WHERE ?1 MEMBER OF e.ofertasEmpleos")
	Empresa findEmpresa(OfertaEmpleo oferta);
	
	Empresa findBycIFempresa(String cif);
	
}
