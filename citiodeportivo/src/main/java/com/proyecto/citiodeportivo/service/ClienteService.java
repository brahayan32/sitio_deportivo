package com.proyecto.citiodeportivo.service;

import com.proyecto.citiodeportivo.entities.ClienteEntity;
import java.util.List;

public interface ClienteService {

    List<ClienteEntity> findAll();

    ClienteEntity findById(Integer id);

    ClienteEntity save(ClienteEntity cliente);

    ClienteEntity update(Integer id, ClienteEntity cliente);

    void delete(Integer id);

    ClienteEntity findByEmail(String email);
}
