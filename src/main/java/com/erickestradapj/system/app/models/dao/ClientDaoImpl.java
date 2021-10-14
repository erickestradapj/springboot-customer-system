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

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return em.createQuery("FROM Client").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return em.find(Client.class, id);
    }

    @Override
    @Transactional
    public void save(Client client) {
        if (client.getId() != null && client.getId() > 0) {
            em.merge(client);
        } else {
            em.persist(client);
        }
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        em.remove(this.findById(id));
    }
}
