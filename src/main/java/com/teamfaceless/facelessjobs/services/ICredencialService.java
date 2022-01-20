package com.teamfaceless.facelessjobs.services;

import java.util.Optional;

import com.teamfaceless.facelessjobs.model.Credencial;

public interface ICredencialService {
    
    Optional<Credencial> findByEmail(String email);
    
}
