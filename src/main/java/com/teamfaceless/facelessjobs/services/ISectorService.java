package com.teamfaceless.facelessjobs.services;

import java.util.List;
import java.util.Optional;

import com.teamfaceless.facelessjobs.model.SectorLaboral;

public interface ISectorService {
	List<SectorLaboral>findAll();
	void create(SectorLaboral sectorLaboral);
	Optional<SectorLaboral>findByNombreSector(String nombreSector);
	Optional<SectorLaboral> findById(Integer idSector);
}
