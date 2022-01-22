package com.teamfaceless.facelessjobs.services.impl;

import java.util.Optional;

import com.teamfaceless.facelessjobs.dao.IRolRepository;
import com.teamfaceless.facelessjobs.model.Rol;
import com.teamfaceless.facelessjobs.services.IRolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService implements IRolService {

	@Autowired
    private IRolRepository rolRepository;

    @Override
    public Rol getRoleById(int id) {

        Optional<Rol> role = rolRepository.findById(id);
        if (role.isPresent()) {
            return role.get();
        }
        return null;

    }

	@Override
	public Optional<Rol> findByUser(String user) {
		return rolRepository.findByUser(user);
	}

}
