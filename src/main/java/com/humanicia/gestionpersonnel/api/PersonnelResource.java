package com.humanicia.gestionpersonnel.api;

import com.humanicia.gestionpersonnel.entities.Personnel;
import com.humanicia.gestionpersonnel.entities.Service;
import com.humanicia.gestionpersonnel.entities.Poste;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/personnels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonnelResource {

    @PersistenceContext(unitName = "GestionPersonnelPU")
    private EntityManager em;

    @GET
    public List<Personnel> getAll() {
        return em.createQuery("SELECT p FROM Personnel p", Personnel.class).getResultList();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        Personnel personnel = em.find(Personnel.class, id);
        if (personnel == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(personnel).build();
    }
@POST
@Transactional
public Response create(Personnel personnel) {
    // Service
    if (personnel.getService() != null) {
        Integer idService = personnel.getService().getIdService();
        if (idService != null) {
            Service service = em.find(Service.class, idService);
            if (service == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Le service avec id " + idService + " n'existe pas.")
                        .build();
            }
            personnel.setService(service);
        }
    }

    // Poste
    if (personnel.getPoste() != null) {
        Integer idPoste = personnel.getPoste().getIdPoste();
        if (idPoste != null) {
            Poste poste = em.find(Poste.class, idPoste);
            if (poste == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Le poste avec id " + idPoste + " n'existe pas.")
                        .build();
            }
            personnel.setPoste(poste);
        }
    }

    em.persist(personnel);
    return Response.status(Response.Status.CREATED).entity(personnel).build();
}

@PUT
@Path("/{id}")
@Transactional
public Response update(@PathParam("id") Integer id, Personnel updated) {
    Personnel existing = em.find(Personnel.class, id);
    if (existing == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    existing.setNom(updated.getNom());
    existing.setPrenom(updated.getPrenom());
    existing.setAdresse(updated.getAdresse());
    existing.setTelephone(updated.getTelephone());
    existing.setEmail(updated.getEmail());
    existing.setDateEmbauche(updated.getDateEmbauche());
    existing.setSalaire(updated.getSalaire());

    // Service
    if (updated.getService() != null) {
        Integer idService = updated.getService().getIdService();
        if (idService != null) {
            Service service = em.find(Service.class, idService);
            if (service == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Le service avec id " + idService + " n'existe pas.")
                        .build();
            }
            existing.setService(service);
        }
    }

    // Poste
    if (updated.getPoste() != null) {
        Integer idPoste = updated.getPoste().getIdPoste();
        if (idPoste != null) {
            Poste poste = em.find(Poste.class, idPoste);
            if (poste == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Le poste avec id " + idPoste + " n'existe pas.")
                        .build();
            }
            existing.setPoste(poste);
        }
    }

    return Response.ok(existing).build();
}

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Personnel existing = em.find(Personnel.class, id);
        if (existing == null) return Response.status(Response.Status.NOT_FOUND).build();
        em.remove(existing);
        return Response.noContent().build();
    }
}
