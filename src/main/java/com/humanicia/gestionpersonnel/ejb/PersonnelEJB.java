package com.humanicia.gestionpersonnel.ejb;

import com.humanicia.gestionpersonnel.entities.Personnel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PersonnelEJB {

    @PersistenceContext(unitName = "GestionPersonnelPU")
    private EntityManager em;

    public void create(Personnel p) {
        em.persist(p);
    }

    public Personnel find(int id) {
        return em.find(Personnel.class, id);
    }

    public void update(Personnel p) {
        em.merge(p);
    }

    public void delete(int id) {
        Personnel p = find(id);
        if (p != null) {
            em.remove(p);
        }
    }

    public List<Personnel> findAll() {
        return em.createQuery("SELECT p FROM Personnel p", Personnel.class).getResultList();
    }
}
