package com.humanicia.gestionpersonnel.ejb;

import com.humanicia.gestionpersonnel.entities.Contrat;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ContratEJB {

    @PersistenceContext(unitName = "GestionPersonnelPU")
    private EntityManager em;

    public void create(Contrat c) {
        em.persist(c);
    }

    public Contrat find(int id) {
        return em.find(Contrat.class, id);
    }

    public void update(Contrat c) {
        em.merge(c);
    }

    public void delete(int id) {
        Contrat c = find(id);
        if (c != null) {
            em.remove(c);
        }
    }

    public List<Contrat> findAll() {
        return em.createQuery("SELECT c FROM Contrat c", Contrat.class).getResultList();
    }
}
