package com.erickestradapj.system.app.services;

import com.erickestradapj.system.app.models.entity.Client;

import java.util.List;

public interface IClientService {
    List<Client> findAll();

    void save(Client client);

    Client findById(Long id);

    void delete(Long id);
}
