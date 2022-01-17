package com.teamfaceless.facelessjobs.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamfaceless.facelessjobs.model.SectorLaboral;

public interface ISectorRepository extends JpaRepository<SectorLaboral, Integer> {
	Optional<SectorLaboral>findByNombreSectorLaboral(String nombreSectorLaboral);
}
