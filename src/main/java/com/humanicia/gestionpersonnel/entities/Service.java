package com.humanicia.gestionpersonnel.entities;

import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;
import java.util.List;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Integer idService;

    @Column(name = "nom_service", nullable = false, length = 100)
    private String nomService;

    @Column(length = 100)
    private String localisation;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonbTransient
    private List<Personnel> personnels;

    public Integer getIdService() { return idService; }
    public String getNomService() { return nomService; }
    public void setNomService(String nomService) { this.nomService = nomService; }
    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }
    public List<Personnel> getPersonnels() { return personnels; }
    public void setPersonnels(List<Personnel> personnels) { this.personnels = personnels; }
}
