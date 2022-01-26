package com.teamfaceless.facelessjobs.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamfaceless.facelessjobs.dao.IHabilidadOfertaRepository;
import com.teamfaceless.facelessjobs.enums.Categoria;
import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadCandidato;
import com.teamfaceless.facelessjobs.model.HabilidadOferta;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.services.IHabilidadOfertaService;
import com.teamfaceless.facelessjobs.services.IHabilidadService;

@Service
public class HabilidadOfertaService implements IHabilidadOfertaService {

	@Autowired
	private IHabilidadOfertaRepository repository;

	@Autowired
	private IHabilidadService habService;

//	@Override
//	public List<HabilidadOferta> findAll() {
//		return repository.findAll();
//	}

//	@Override
//	public void create(HabilidadOferta habilidadOferta) {
//		repository.save(habilidadOferta);		
//	}

//	@Override
//	public Optional<HabilidadOferta> findById(Integer id) {
//		return repository.findById(id);
//	}

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

//	@Override
//	public List<HabilidadOferta> findHabilidadesOfertaByOferta(OfertaEmpleo oferta) {
//		return repository.findHabilidadesOfertaByOferta(oferta);
//	}

//	@Override
//	public List<Habilidad> findHabilidadesByOferta(OfertaEmpleo oferta) {
//		return repository.findHabilidadesByOferta(oferta);
//	}

//	@Override
//	public List<Habilidad> findHabilidadesRestantesByOferta(OfertaEmpleo oferta) {
//		List<Habilidad> listaCompleta = habService.findAll();
//		List<Habilidad> listaHabilidadEnOferta = findHabilidadesByOferta(oferta);
//		listaCompleta.removeAll(listaHabilidadEnOferta);
//		return listaCompleta;
//	}

	@Override
	public HabilidadOferta findHabilidadOfertaByOfertaAndHabilidad(OfertaEmpleo oferta, Habilidad habilidad) {
		return repository.findHabilidadOfertaByOfertaAndHabilidad(oferta, habilidad);
	}

	@Override
	public List<HabilidadOferta> findHabilidadesOfertaDurasByOferta(OfertaEmpleo oferta) {
		return repository.findHabilidadesOfertaDurasByOferta(oferta.getIdOfertaEmpleo());
	}

	@Override
	public List<HabilidadOferta> findHabilidadesOfertaBlandasByOferta(OfertaEmpleo oferta) {
		return repository.findHabilidadesOfertaBlandasByOferta(oferta.getIdOfertaEmpleo());
	}

	@Override
	public List<Habilidad> findHabilidadesDurasRestantesByOferta(OfertaEmpleo oferta) {
		List<Integer> listaId = repository.findHabilidadesDurasRestantesByOferta(oferta.getIdOfertaEmpleo());
		List<Habilidad> listaHabilidades = new ArrayList<>();
		for (Integer id : listaId) {
			Habilidad nuevaHabilidad = habService.findById(id).get();
			listaHabilidades.add(nuevaHabilidad);
		}
		return listaHabilidades;
	}

	@Override
	public List<Habilidad> findHabilidadesBlandasRestantesByOferta(OfertaEmpleo oferta) {
		List<Integer> listaId = repository.findHabilidadesBlandasRestantesByOferta(oferta.getIdOfertaEmpleo());
		List<Habilidad> listaHabilidades = new ArrayList<>();
		for (Integer id : listaId) {
			Habilidad nuevaHabilidad = habService.findById(id).get();
			listaHabilidades.add(nuevaHabilidad);
		}
		return listaHabilidades;
	}

	/**
	 * @author Mefisto
	 * Devuelve la lista de Habilidades de una oferta
	 * @param habilidadesOferta
	 * @return Lista de Habilidad de una oferta
	 */
	@Override
	public List<Habilidad> generalizacionHabilidadesOferta(List<HabilidadOferta> habilidadesOferta) {
		// Desmonto las habilidades de la oferta a habilidades simples
		List<Habilidad> habilidadesRequeridas = new ArrayList<>();
		for (HabilidadOferta habOfer : habilidadesOferta) {
			habilidadesRequeridas.add(habOfer.getHabilidad());
		}
		return habilidadesRequeridas;
	}

	/**
	 * @author Mefisto
	 * Devuelve la especificacion de de las habilidades de una oferta
	 * @param habilidades
	 * @param OfertaEmpleo
	 * @return Lista HabilidadOferta
	 */
	@Override
	public List<HabilidadOferta> especializacionHabilidadesOferta(List<Habilidad> habilidades, OfertaEmpleo ofertaEmpleo) {
	
		List<HabilidadOferta> habilidadesOferta = ofertaEmpleo.getHabilidadOfertaList();
		List<HabilidadOferta> habilidadesComprobadas = new ArrayList<>();
		
		for (Habilidad habilidad : habilidades) {
			for (HabilidadOferta habOfer : habilidadesOferta) {
				if (habOfer.getHabilidad().equals(habilidad)) {
					habilidadesComprobadas.add(habOfer);
					break;
				}
			}
		}
		return habilidadesComprobadas;
	}

	/**
	 * @author Mefisto
	 * Devuelve Las habilidades duras requeridas de una oferta
	 */
	@Override
	public List<HabilidadOferta> habilidadesDurasRequeridas(List<HabilidadOferta> habilidadOfertas) {
		
		List<HabilidadOferta> habilidadesFiltradas = new ArrayList<>();
		for (HabilidadOferta habOfer : habilidadOfertas) {
			if (habOfer.isObligatorio() && habOfer.getHabilidad().getCategoriaHabilidad()==Categoria.DURA) {
				habilidadesFiltradas.add(habOfer);
			}
		}
		return habilidadesFiltradas;
	}

	/**
	 * @author Mefisto
	 * Devuelve las habilidades Duras no requeridas
	 */
	@Override
	public List<HabilidadOferta> habilidadesDurasNoRequeridas(List<HabilidadOferta> habilidadOfertas) {
		
		List<HabilidadOferta> habilidadesFiltradas = new ArrayList<>();
		for (HabilidadOferta habOfer : habilidadOfertas) {
			if (habOfer.isObligatorio()==false && habOfer.getHabilidad().getCategoriaHabilidad()==Categoria.DURA) {
				habilidadesFiltradas.add(habOfer);
			}
		}
		return habilidadesFiltradas;
	}

	/**
	 * @author Mefisto
	 * Devuelve las habilidades Blandas requeridas
	 */
	@Override
	public List<HabilidadOferta> habilidadesBlandasRequeridas(List<HabilidadOferta> habilidadOfertas) {
		
		List<HabilidadOferta> habilidadesFiltradas = new ArrayList<>();
		for (HabilidadOferta habOfer : habilidadOfertas) {
			if (habOfer.getHabilidad().getCategoriaHabilidad()==Categoria.BLANDA) {
				habilidadesFiltradas.add(habOfer);
			}
		}
		return habilidadesFiltradas;
	}

}
