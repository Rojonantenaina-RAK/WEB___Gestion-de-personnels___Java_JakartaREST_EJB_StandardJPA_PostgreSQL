package com.humanicia.gestionpersonnel.ejb;

import com.humanicia.gestionpersonnel.entities.Projet;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProjetEJB {

    @PersistenceContext(unitName = "GestionPersonnelPU")
    private EntityManager em;

    public void create(Projet p) {
        em.persist(p);
    }

    public Projet find(int id) {
        return em.find(Projet.class, id);
    }

    public void update(Projet p) {
        em.merge(p);
    }

    public void delete(int id) {
        Projet p = find(id);
        if (p != null) {
            em.remove(p);
        }
    }

    public List<Projet> findAll() {
        return em.createQuery("SELECT p FROM Projet p", Projet.class).getResultList();
    }
}
