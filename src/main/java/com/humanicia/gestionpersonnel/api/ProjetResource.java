package com.humanicia.gestionpersonnel.api;

import com.humanicia.gestionpersonnel.entities.Projet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/projets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjetResource {

    @PersistenceContext
    private EntityManager em;

    @GET
    public List<Projet> getAll() {
        return em.createQuery("SELECT p FROM Projet p", Projet.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Projet getById(@PathParam("id") Integer id) {
        return em.find(Projet.class, id);
    }

    @POST
    @Transactional
    public Projet create(Projet projet) {
        em.persist(projet);
        return projet;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Projet update(@PathParam("id") Integer id, Projet data) {
        Projet p = em.find(Projet.class, id);
        if (p == null) throw new NotFoundException();
        p.setNomProjet(data.getNomProjet());
        p.setObjectif(data.getObjectif());
        p.setDateDebut(data.getDateDebut());
        p.setDateFin(data.getDateFin());
        return p;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Integer id) {
        Projet p = em.find(Projet.class, id);
        if (p != null) em.remove(p);
    }
}
