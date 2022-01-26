package com.teamfaceless.facelessjobs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamfaceless.facelessjobs.dao.IHabilidadOfertaRepository;
import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadOferta;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.services.IHabilidadOfertaService;
import com.teamfaceless.facelessjobs.services.IHabilidadService;

@Service
public class HabilidadOfertaService implements IHabilidadOfertaService{
	
	@Autowired
	private IHabilidadOfertaRepository repository;
	
	@Autowired
	private IHabilidadService habService;
	
	@Override
	public List<HabilidadOferta> findAll() {
		return repository.findAll();
	}

	@Override
	public void create(HabilidadOferta habilidadOferta) {
		repository.save(habilidadOferta);		
	}

	@Override
	public Optional<HabilidadOferta> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void modify(HabilidadOferta habilidadOferta) {
		repository.save(habilidadOferta);		
	}

	@Override
	@Transactional
	public void delete(HabilidadOferta habilidadOferta) {
		OfertaEmpleo oferta = habilidadOferta.getOfertaEmpleo();
		Habilidad habilidad = habilidadOferta.getHabilidad();
		repository.deleteHabilidadOferta(oferta, habilidad);
	}

	@Override
	public List<HabilidadOferta> findHabilidadesOfertaByOferta(OfertaEmpleo oferta) {
		return repository.findHabilidadesOfertaByOferta(oferta);
	}

	@Override
	public List<Habilidad> findHabilidadesByOferta(OfertaEmpleo oferta) {
		return repository.findHabilidadesByOferta(oferta);
	}

	@Override
	public List<Habilidad> findHabilidadesRestantesByOferta(OfertaEmpleo oferta) {
		List<Habilidad> listaCompleta = habService.findAll();
		List<Habilidad> listaHabilidadEnOferta = findHabilidadesByOferta(oferta);
		listaCompleta.removeAll(listaHabilidadEnOferta);
		return listaCompleta;
	}

	@Override
	public HabilidadOferta findHabilidadOfertaByOfertaAndHabilidad(OfertaEmpleo oferta, Habilidad habilidad) {
		return repository.findHabilidadOfertaByOfertaAndHabilidad(oferta, habilidad);
	}

	@Override
	public List<HabilidadOferta> findHabilidadesOfertaDurasByOferta(OfertaEmpleo oferta) {
		return repository.findHabilidadesDuras(oferta.getIdOfertaEmpleo());
	}
	
	@Override
	public List<HabilidadOferta> findHabilidadesOfertaBlandasByOferta(OfertaEmpleo oferta) {
		return repository.findHabilidadesBlandas(oferta.getIdOfertaEmpleo());
	}

	@Override
	public List<Habilidad> findHabilidadesDurasRestantesByOferta(OfertaEmpleo oferta) {
		return repository.findHabilidadesDurasRestantesByOferta(oferta.getIdOfertaEmpleo());
	}

	@Override
	public List<Habilidad> findHabilidadesBlandasRestantesByOferta(OfertaEmpleo oferta) {
		return repository.findHabilidadesBlandasRestantesByOferta(oferta.getIdOfertaEmpleo());
	}
	
}
