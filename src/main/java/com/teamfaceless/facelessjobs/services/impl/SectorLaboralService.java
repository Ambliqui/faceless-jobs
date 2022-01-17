package com.teamfaceless.facelessjobs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dao.ISectorRepository;
import com.teamfaceless.facelessjobs.model.SectorLaboral;
import com.teamfaceless.facelessjobs.services.ISectorService;

@Service
public class SectorLaboralService implements ISectorService{

	@Autowired
	private ISectorRepository repository;
	
	@Override
	public void create(SectorLaboral sectorLaboral) {
		if(!isPresent(sectorLaboral)) {
			repository.save(sectorLaboral);
		}
	}

	@Override
	public Optional<SectorLaboral> findByNombreSector(String nombreSector) {
		return repository.findByNombreSectorLaboral(nombreSector);
	}
	
	private boolean isPresent(SectorLaboral laboral) {
		return repository.findByNombreSectorLaboral(laboral.getNombreSectorLaboral()).isPresent();
	}

	@Override
	public List<SectorLaboral> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<SectorLaboral> findById(Integer idSector) {
		return repository.findById(idSector);
	}

	
	
}
