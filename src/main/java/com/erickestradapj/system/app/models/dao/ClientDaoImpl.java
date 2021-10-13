package com.erickestradapj.system.app.models.dao;

import com.erickestradapj.system.app.models.entity.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClientDaoImpl implements IClientDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<Client> findAll() {
        return em.createQuery("FROM Client").getResultList();
    }
}
