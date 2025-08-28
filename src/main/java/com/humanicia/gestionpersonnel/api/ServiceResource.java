package com.humanicia.gestionpersonnel.api;

import com.humanicia.gestionpersonnel.entities.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/services")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceResource {

    @PersistenceContext
    private EntityManager em;

    @GET
    public List<Service> getAll() {
        return em.createQuery("SELECT s FROM Service s", Service.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Service getById(@PathParam("id") Integer id) {
        return em.find(Service.class, id);
    }

    @POST
    @Transactional
    public Service create(Service service) {
        em.persist(service);
        return service;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Service update(@PathParam("id") Integer id, Service data) {
        Service s = em.find(Service.class, id);
        if (s == null) throw new NotFoundException();
        s.setNomService(data.getNomService());
        s.setLocalisation(data.getLocalisation());
        return s;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Integer id) {
        Service s = em.find(Service.class, id);
        if (s != null) em.remove(s);
    }
}
