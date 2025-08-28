package com.humanicia.gestionpersonnel.api;

import com.humanicia.gestionpersonnel.entities.Tache;
import com.humanicia.gestionpersonnel.entities.Projet;
import com.humanicia.gestionpersonnel.entities.Personnel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/taches")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TacheResource {

    @PersistenceContext
    private EntityManager em;

    @GET
    public List<Tache> getAll() {
        return em.createQuery("SELECT t FROM Tache t", Tache.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Tache getById(@PathParam("id") Integer id) {
        return em.find(Tache.class, id);
    }

    @POST
    @Transactional
    public Tache create(Tache tache) {
        // lier Projet si fourni
        if (tache.getProjet() != null && tache.getProjet().getIdProjet() != null) {
            Projet pr = em.find(Projet.class, tache.getProjet().getIdProjet());
            tache.setProjet(pr);
        }
        // lier Personnel si fourni
        if (tache.getPersonnel() != null && tache.getPersonnel().getMatricule() != null) {
            Personnel p = em.find(Personnel.class, tache.getPersonnel().getMatricule());
            tache.setPersonnel(p);
        }
        em.persist(tache);
        return tache;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Tache update(@PathParam("id") Integer id, Tache data) {
        Tache t = em.find(Tache.class, id);
        if (t == null) throw new NotFoundException();

        t.setLibelle(data.getLibelle());
        t.setDescription(data.getDescription());
        t.setDateDebut(data.getDateDebut());
        t.setDateFin(data.getDateFin());
        t.setStatut(data.getStatut());

        if (data.getProjet() != null && data.getProjet().getIdProjet() != null) {
            Projet pr = em.find(Projet.class, data.getProjet().getIdProjet());
            t.setProjet(pr);
        }

        if (data.getPersonnel() != null && data.getPersonnel().getMatricule() != null) {
            Personnel p = em.find(Personnel.class, data.getPersonnel().getMatricule());
            t.setPersonnel(p);
        }

        return t;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Integer id) {
        Tache t = em.find(Tache.class, id);
        if (t != null) em.remove(t);
    }
}
