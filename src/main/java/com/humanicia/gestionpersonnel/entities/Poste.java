package com.humanicia.gestionpersonnel.entities;

import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;
import java.util.List;

@Entity
@Table(name = "poste")
public class Poste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_poste")
    private Integer idPoste;

    @Column(name = "libelle", nullable = false, length = 100)
    private String libelle;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "poste", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonbTransient
    private List<Personnel> personnels;

    public Integer getIdPoste() { return idPoste; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Personnel> getPersonnels() { return personnels; }
    public void setPersonnels(List<Personnel> personnels) { this.personnels = personnels; }
}
    