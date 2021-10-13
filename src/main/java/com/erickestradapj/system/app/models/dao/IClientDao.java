package com.erickestradapj.system.app.models.dao;

import com.erickestradapj.system.app.models.entity.Client;

import java.util.List;

public interface IClientDao {
    List<Client> findAll();
}
