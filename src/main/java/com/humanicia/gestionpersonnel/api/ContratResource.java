package com.humanicia.gestionpersonnel.api;

import com.humanicia.gestionpersonnel.entities.Contrat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/contrats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContratResource {

    @PersistenceContext(unitName = "GestionPersonnelPU")
    private EntityManager em;

    @GET
    public List<Contrat> getAll() {
        return em.createQuery("SELECT c FROM Contrat c", Contrat.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Contrat getById(@PathParam("id") Integer id) {
        return em.find(Contrat.class, id);
    }

    @POST
    @Transactional
    public Contrat create(Contrat contrat) {
        em.persist(contrat);
        return contrat;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Contrat update(@PathParam("id") Integer id, Contrat data) {
        Contrat c = em.find(Contrat.class, id);
        if (c == null) throw new NotFoundException();
        c.setDateDebut(data.getDateDebut());
        c.setDateFin(data.getDateFin());
        c.setTypeContrat(data.getTypeContrat());
        c.setPersonnel(data.getPersonnel());
        return c;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Integer id) {
        Contrat c = em.find(Contrat.class, id);
        if (c != null) em.remove(c);
    }
}
