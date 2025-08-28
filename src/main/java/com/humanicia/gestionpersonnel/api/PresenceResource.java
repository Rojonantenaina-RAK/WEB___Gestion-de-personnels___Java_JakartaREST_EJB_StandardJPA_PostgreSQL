package com.humanicia.gestionpersonnel.api;

import com.humanicia.gestionpersonnel.entities.Presence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
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
    public Presence getById(@PathParam("id") Integer id) {
        return em.find(Presence.class, id);
    }

    @POST
    @Transactional
    public Presence create(Presence presence) {
        em.persist(presence);
        return presence;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Presence update(@PathParam("id") Integer id, Presence data) {
        Presence p = em.find(Presence.class, id);
        if (p == null) throw new NotFoundException();
        p.setDatePresence(data.getDatePresence());
        p.setStatut(data.getStatut());
        p.setPersonnel(data.getPersonnel());
        return p;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Integer id) {
        Presence p = em.find(Presence.class, id);
        if (p != null) em.remove(p);
    }
}
