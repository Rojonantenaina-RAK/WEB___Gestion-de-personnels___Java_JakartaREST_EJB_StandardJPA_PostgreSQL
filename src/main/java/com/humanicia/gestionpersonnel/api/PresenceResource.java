package com.humanicia.gestionpersonnel.api;

import com.humanicia.gestionpersonnel.entities.Personnel;
import com.humanicia.gestionpersonnel.entities.Presence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/presences")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PresenceResource {

    @PersistenceContext(unitName = "GestionPersonnelPU")
    private EntityManager em;

    @GET
    public List<Presence> getAll() {
        return em.createQuery("SELECT p FROM Presence p", Presence.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        Presence presence = em.find(Presence.class, id);
        if (presence == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(presence).build();
    }
@POST
@Transactional
public Response create(Presence presence) {
    if (presence.getPersonnel() != null) {
        Integer matricule = presence.getPersonnel().getMatricule();
        if (matricule != null) {
            Personnel pers = em.find(Personnel.class, matricule);
            if (pers == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Le personnel avec matricule " + matricule + " n'existe pas.")
                        .build();
            }
            presence.setPersonnel(pers);
        }
    }

    em.persist(presence);
    return Response.status(Response.Status.CREATED).entity(presence).build();
}

@PUT
@Path("/{id}")
@Transactional
public Response update(@PathParam("id") Integer id, Presence updated) {
    Presence existing = em.find(Presence.class, id);
    if (existing == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    existing.setDatePresence(updated.getDatePresence());
    existing.setHeureArrivee(updated.getHeureArrivee());
    existing.setHeureDepart(updated.getHeureDepart());
    existing.setStatut(updated.getStatut());

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
        Presence p = em.find(Presence.class, id);
        if (p == null) return Response.status(Response.Status.NOT_FOUND).build();
        em.remove(p);
        return Response.noContent().build();
    }
}
