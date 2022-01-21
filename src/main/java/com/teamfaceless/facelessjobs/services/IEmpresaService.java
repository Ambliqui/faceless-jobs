package com.teamfaceless.facelessjobs.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.teamfaceless.facelessjobs.dtos.empresa.EmpresaRegistroDto;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;


public interface IEmpresaService {

	List<Empresa> findAll();
	Optional<Empresa> findById(Integer id);
	Empresa findEmpresa(OfertaEmpleo oferta);
	void create(Empresa empresa);
	void delete (Empresa empresa);
	void delete (Integer idEmpresa);
	void modify (Empresa empresa);
	boolean isPresent(Empresa empresa);
	Map <String, String> validateRegister(EmpresaRegistroDto empresaRegistroDto);
	
}
