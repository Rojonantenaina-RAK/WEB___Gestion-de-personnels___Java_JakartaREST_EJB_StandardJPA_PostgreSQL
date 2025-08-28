package com.humanicia.gestionpersonnel.ejb;

import com.humanicia.gestionpersonnel.entities.Service;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ServiceEJB {

    @PersistenceContext(unitName = "GestionPersonnelPU")
    private EntityManager em;

    public void create(Service service) {
        em.persist(service);
    }

    public Service find(int id) {
        return em.find(Service.class, id);
    }

    public void update(Service service) {
        em.merge(service);
    }

    public void delete(int id) {
        Service s = find(id);
        if (s != null) {
            em.remove(s);
        }
    }

    public List<Service> findAll() {
        return em.createQuery("SELECT s FROM Service s", Service.class).getResultList();
    }
}
