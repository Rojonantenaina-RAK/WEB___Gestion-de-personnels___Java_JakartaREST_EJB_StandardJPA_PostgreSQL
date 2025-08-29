package com.humanicia.gestionpersonnel.api;

import com.humanicia.gestionpersonnel.entities.Tache;
import com.humanicia.gestionpersonnel.entities.Projet;
import com.humanicia.gestionpersonnel.entities.Personnel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/taches")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TacheResource {

    @PersistenceContext(unitName = "GestionPersonnelPU")
    private EntityManager em;

    @GET
    public List<Tache> getAll() {
        return em.createQuery("SELECT t FROM Tache t", Tache.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        Tache t = em.find(Tache.class, id);
        if (t == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(t).build();
    }
@POST
@Transactional
public Response create(Tache tache) {
    // Projet
    if (tache.getProjet() != null) {
        Integer idProjet = tache.getProjet().getIdProjet();
        if (idProjet != null) {
            Projet projet = em.find(Projet.class, idProjet);
            if (projet == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Le projet avec id " + idProjet + " n'existe pas.")
                        .build();
            }
            tache.setProjet(projet);
        }
    }

    // Personnel
    if (tache.getPersonnel() != null) {
        Integer matricule = tache.getPersonnel().getMatricule();
        if (matricule != null) {
            Personnel pers = em.find(Personnel.class, matricule);
            if (pers == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Le personnel avec matricule " + matricule + " n'existe pas.")
                        .build();
            }
            tache.setPersonnel(pers);
        }
    }

    em.persist(tache);
    return Response.status(Response.Status.CREATED).entity(tache).build();
}

@PUT
@Path("/{id}")
@Transactional
public Response update(@PathParam("id") Integer id, Tache updated) {
    Tache existing = em.find(Tache.class, id);
    if (existing == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    existing.setLibelle(updated.getLibelle());
    existing.setDescription(updated.getDescription());
    existing.setDateDebut(updated.getDateDebut());
    existing.setDateFin(updated.getDateFin());
    existing.setStatut(updated.getStatut());

    // Projet
    if (updated.getProjet() != null) {
        Integer idProjet = updated.getProjet().getIdProjet();
        if (idProjet != null) {
            Projet projet = em.find(Projet.class, idProjet);
            if (projet == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Le projet avec id " + idProjet + " n'existe pas.")
                        .build();
            }
            existing.setProjet(projet);
        }
    }

    // Personnel
    if (updated.getPersonnel() != null) {
        Integer matricule = updated.getPersonnel().getMatricule();
        if (matricule != null) {
            Personnel pers = em.find(Personnel.class, matricule);
            if (pers == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Le personnel avec matricule " + matricule + " n'existe pas.")
                        .build();
            }
            existing.setPersonnel(pers);
        }
    }

    return Response.ok(existing).build();
}

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Tache t = em.find(Tache.class, id);
        if (t == null) return Response.status(Response.Status.NOT_FOUND).build();
        em.remove(t);
        return Response.noContent().build();
    }
}
