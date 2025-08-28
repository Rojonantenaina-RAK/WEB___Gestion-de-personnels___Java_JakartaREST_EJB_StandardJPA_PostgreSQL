package com.humanicia.gestionpersonnel.ejb;

import com.humanicia.gestionpersonnel.entities.Poste;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PosteEJB {

    @PersistenceContext(unitName = "GestionPersonnelPU")
    private EntityManager em;

    public void create(Poste poste) {
        em.persist(poste);
    }

    public Poste find(int id) {
        return em.find(Poste.class, id);
    }

    public void update(Poste poste) {
        em.merge(poste);
    }

    public void delete(int id) {
        Poste p = find(id);
        if (p != null) {
            em.remove(p);
        }
    }

    public List<Poste> findAll() {
        return em.createQuery("SELECT p FROM Poste p", Poste.class).getResultList();
    }
}
