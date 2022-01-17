package com.teamfaceless.facelessjobs.services.impl;

import java.util.List;
import java.util.Optional;

import com.teamfaceless.facelessjobs.dao.IEmpresaRepository;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.services.IEmpresaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService implements IEmpresaService {

	@Autowired
	private IEmpresaRepository repository;

	@Override
	public List<Empresa> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Empresa> findById(Integer id) {
		return repository.findById(id);
	}
	
	@Override
	public void create(Empresa empresa) {
		repository.save(empresa);
	}

	@Override
	public void delete(Empresa empresa) {
		repository.delete(empresa);

	}

	@Override
	public void delete(Integer idEmpresa) {
		repository.deleteById(idEmpresa);

	}

	@Override
	public void modify(Empresa empresa) {
		repository.save(empresa);
	}

	@Override
	public Empresa findEmpresa(OfertaEmpleo oferta) {
		return repository.findEmpresa(oferta);
	}

	@Override
	public boolean isPresent(Empresa empresa) {
		return repository.findByEmailEmpresa(empresa.getCredencial().getEmail()).isPresent();
	}

}
