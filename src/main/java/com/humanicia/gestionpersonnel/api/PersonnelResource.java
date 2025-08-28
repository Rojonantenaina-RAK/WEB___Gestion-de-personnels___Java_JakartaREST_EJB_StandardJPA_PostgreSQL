package com.humanicia.gestionpersonnel.api;

import com.humanicia.gestionpersonnel.entities.Personnel;
import com.humanicia.gestionpersonnel.entities.Service;
import com.humanicia.gestionpersonnel.entities.Poste;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/personnels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonnelResource {

    @PersistenceContext
    private EntityManager em;

    @GET
    public List<Personnel> getAll() {
        return em.createQuery("SELECT p FROM Personnel p", Personnel.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Personnel getById(@PathParam("id") Integer id) {
        return em.find(Personnel.class, id);
    }

    @POST
    @Transactional
    public Personnel create(Personnel personnel) {
        em.persist(personnel);
        return personnel;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Personnel update(@PathParam("id") Integer id, Personnel data) {
        Personnel p = em.find(Personnel.class, id);
        if (p == null) throw new NotFoundException();

        // Mise à jour des champs simples
        p.setNom(data.getNom());
        p.setPrenom(data.getPrenom());
        p.setAdresse(data.getAdresse());
        p.setTelephone(data.getTelephone());
        p.setEmail(data.getEmail());
        p.setDateEmbauche(data.getDateEmbauche());
        p.setSalaire(data.getSalaire());

        // Mise à jour des relations
        if (data.getService() != null) {
            Service s = em.find(Service.class, data.getService().getIdService());
            p.setService(s);
        }
        if (data.getPoste() != null) {
            Poste pos = em.find(Poste.class, data.getPoste().getIdPoste());
            p.setPoste(pos);
        }

        return p;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Integer id) {
        Personnel p = em.find(Personnel.class, id);
        if (p != null) em.remove(p);
    }
}
