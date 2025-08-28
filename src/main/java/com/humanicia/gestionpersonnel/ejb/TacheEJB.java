package com.humanicia.gestionpersonnel.ejb;

import com.humanicia.gestionpersonnel.entities.Tache;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TacheEJB {

    @PersistenceContext(unitName = "GestionPersonnelPU")
    private EntityManager em;

    public void create(Tache t) {
        em.persist(t);
    }

    public Tache find(int id) {
        return em.find(Tache.class, id);
    }

    public void update(Tache t) {
        em.merge(t);
    }

    public void delete(int id) {
        Tache t = find(id);
        if (t != null) {
            em.remove(t);
        }
    }

    public List<Tache> findAll() {
        return em.createQuery("SELECT t FROM Tache t", Tache.class).getResultList();
    }
}
