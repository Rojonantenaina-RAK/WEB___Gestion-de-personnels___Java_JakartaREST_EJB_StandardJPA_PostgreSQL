package com.humanicia.gestionpersonnel.ejb;

import com.humanicia.gestionpersonnel.entities.Presence;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PresenceEJB {

    @PersistenceContext(unitName = "GestionPersonnelPU")
    private EntityManager em;

    public void create(Presence p) {
        em.persist(p);
    }

    public Presence find(int id) {
        return em.find(Presence.class, id);
    }

    public void update(Presence p) {
        em.merge(p);
    }

    public void delete(int id) {
        Presence p = find(id);
        if (p != null) {
            em.remove(p);
        }
    }

    public List<Presence> findAll() {
        return em.createQuery("SELECT p FROM Presence p", Presence.class).getResultList();
    }
}
