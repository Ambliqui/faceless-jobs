package com.teamfaceless.facelessjobs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teamfaceless.facelessjobs.enums.Categoria;
import com.teamfaceless.facelessjobs.model.Habilidad;

public interface IHabilidadRepository extends JpaRepository<Habilidad, Integer>{
	
	@Query("FROM Habilidad WHERE categoriaHabilidad = :categoria")
	List<Habilidad> findHabilidadByCategoria(@Param("categoria") Categoria categoria);
	
	@Query(value="SELECT * FROM habilidad WHERE categoria_habilidad=1 ORDER BY nombre_habilidad ASC",nativeQuery=true)
	List<Habilidad> findHabilidadesDuras();
	
	@Query(value="SELECT * FROM habilidad WHERE categoria_habilidad=0 ORDER BY nombre_habilidad ASC",nativeQuery=true)
	List<Habilidad> findHabilidadesBlandas();
}
