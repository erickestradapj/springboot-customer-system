package com.erickestradapj.system.app.models.dao;

import com.erickestradapj.system.app.models.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientDao extends CrudRepository<Client, Long> {

}

