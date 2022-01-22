package com.teamfaceless.facelessjobs.dao;

import com.teamfaceless.facelessjobs.model.Rol;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IRolRepository extends CrudRepository<Rol, Integer> {

	@Query(value = "SELECT rol.*"
	+ " FROM ((rol INNER JOIN credencial_roles"
		+ " ON rol.id_rol = roles_id_rol)"
		+ " INNER JOIN credencial"
    + " ON credencial.id_credencial = credencial_id_credencial)"
    + " WHERE email_credencial = ?1",
    nativeQuery = true)
	Optional<Rol> findByUser(String email);
}
