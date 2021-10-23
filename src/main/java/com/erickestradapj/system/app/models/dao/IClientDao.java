package com.erickestradapj.system.app.models.dao;

import com.erickestradapj.system.app.models.entity.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IClientDao extends PagingAndSortingRepository<Client, Long> {

}

