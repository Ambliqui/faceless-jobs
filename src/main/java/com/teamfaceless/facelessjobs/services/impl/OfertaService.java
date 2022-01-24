package com.teamfaceless.facelessjobs.services.impl;

import java.util.List;
import java.util.Optional;

import com.teamfaceless.facelessjobs.dao.IOfertaRepository;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.services.IOfertaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class OfertaService implements IOfertaService {

	@Autowired
	private IOfertaRepository repository;
		@Override
		public List<OfertaEmpleo> findAll() {
			return repository.findAll();
		}

		@Override
		public Optional<OfertaEmpleo> findById(Integer id) {
			return repository.findById(id);
		}

		@Override
		public void create(OfertaEmpleo oferta) {
			repository.save(oferta);
			
		}

		@Override
		public void delete(OfertaEmpleo oferta) {
			repository.delete(oferta);
			
		}

		@Override
		public void delete(Integer idOferta) {
			repository.deleteById(idOferta);
			
		}

		@Override
		public void modify(OfertaEmpleo oferta) {
			repository.save(oferta);
			
		}

		@Override
		public List<OfertaEmpleo> findOfertaByEmpresa(Integer idEmpresa) {
			return repository.findOfertaByidEmpresa(idEmpresa);
		}

		@Override
		public Page<OfertaEmpleo> findAllPageable(Pageable page) {
			return repository.findAll(page);
		}

		@Override
		public List<OfertaEmpleo> findOfertaByidCandidato(Integer idCandidato) {
			return repository.findOfertaByidCandidato(idCandidato);
		}


		public List<OfertaEmpleo> findByTituloAndDescripcion(String titulo, String descrpcion,String provincia,
				String sector,Integer salarioMinimo, Integer salarioMaximo,Integer nPagina,Integer nElementos) {
			return repository.findByTituloAndDescripcion(titulo, descrpcion, provincia, 
					sector,salarioMinimo,salarioMaximo,PageRequest.of(nPagina, nElementos,Sort.by("fechaInicioOferta").descending()));
		}

	

	

	

			

}
