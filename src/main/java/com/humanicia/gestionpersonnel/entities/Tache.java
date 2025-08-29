package com.humanicia.gestionpersonnel.entities;

import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;
import java.time.LocalDate;

@Entity
@Table(name = "tache")
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tache")
    private Integer idTache;

    @Column(nullable = false, length = 100)
    private String libelle;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(length = 20)
    private String statut;

    @ManyToOne
    @JoinColumn(name = "matricule")
    private Personnel personnel;

    @ManyToOne
    @JoinColumn(name = "id_projet")
    private Projet projet;

    // getters & setters
    public Integer getIdTache() { return idTache; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public Personnel getPersonnel() { return personnel; }
    public void setPersonnel(Personnel personnel) { this.personnel = personnel; }
    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }
}
