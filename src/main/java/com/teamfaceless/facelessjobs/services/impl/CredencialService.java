package com.teamfaceless.facelessjobs.services.impl;

import java.util.Optional;

import com.teamfaceless.facelessjobs.dao.ICredencialRepository;
import com.teamfaceless.facelessjobs.model.Credencial;
import com.teamfaceless.facelessjobs.services.ICredencialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredencialService implements ICredencialService {

    @Autowired
    private ICredencialRepository repository;

    @Override
    public Optional<Credencial> findByEmail(String email) {
        return repository.findByEmail(email);
    }


    
}
