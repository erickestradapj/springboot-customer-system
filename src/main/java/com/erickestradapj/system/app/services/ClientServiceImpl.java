package com.erickestradapj.system.app.services;

import com.erickestradapj.system.app.models.dao.IClientDao;
import com.erickestradapj.system.app.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientDao clientDao;

    @Override

    public List<Client> findAll() {
        return (List<Client>) clientDao.findAll();
    }

    @Override
    public void save(Client client) {
        clientDao.save(client);
    }

    @Override
    public Client findById(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        clientDao.deleteById(id);
    }
}
