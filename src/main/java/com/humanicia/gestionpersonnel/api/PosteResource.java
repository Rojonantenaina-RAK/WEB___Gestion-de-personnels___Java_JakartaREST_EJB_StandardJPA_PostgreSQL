package com.humanicia.gestionpersonnel.api;

import com.humanicia.gestionpersonnel.entities.Poste;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import jakarta.persistence.*;

@Path("/postes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PosteResource {

    @PersistenceContext(unitName = "GestionPersonnelPU")
    private EntityManager em;

    @GET
    public List<Poste> getAllPostes() {
        return em.createQuery("SELECT p FROM Poste p", Poste.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Poste getPosteById(@PathParam("id") Integer id) {
        return em.find(Poste.class, id);
    }

    @POST
    public Response createPoste(Poste poste) {
        em.getTransaction().begin();
        em.persist(poste);
        em.getTransaction().commit();
        return Response.status(Response.Status.CREATED).entity(poste).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePoste(@PathParam("id") Integer id, Poste poste) {
        Poste existing = em.find(Poste.class, id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.getTransaction().begin();
        existing.setLibelle(poste.getLibelle());
        existing.setDescription(poste.getDescription());
        em.getTransaction().commit();
        return Response.ok(existing).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePoste(@PathParam("id") Integer id) {
        Poste existing = em.find(Poste.class, id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.getTransaction().begin();
        em.remove(existing);
        em.getTransaction().commit();
        return Response.noContent().build();
    }
}
