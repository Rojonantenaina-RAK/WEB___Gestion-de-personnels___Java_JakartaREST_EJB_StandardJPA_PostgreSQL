package com.humanicia.gestionpersonnel.entities;

import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "presence")
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presence")
    private Integer idPresence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricule", nullable = false)
    private Personnel personnel;

    @Column(name = "date_presence", nullable = false)
    private LocalDate datePresence;

    @Column(name = "heure_arrivee")
    private LocalTime heureArrivee;

    @Column(name = "heure_depart")
    private LocalTime heureDepart;

    @Column(nullable = false, length = 20)
    private String statut;

    public Integer getIdPresence() { return idPresence; }
    @JsonbTransient
    public Personnel getPersonnel() { return personnel; }
    public void setPersonnel(Personnel personnel) { this.personnel = personnel; }
    public LocalDate getDatePresence() { return datePresence; }
    public void setDatePresence(LocalDate datePresence) { this.datePresence = datePresence; }
    public LocalTime getHeureArrivee() { return heureArrivee; }
    public void setHeureArrivee(LocalTime heureArrivee) { this.heureArrivee = heureArrivee; }
    public LocalTime getHeureDepart() { return heureDepart; }
    public void setHeureDepart(LocalTime heureDepart) { this.heureDepart = heureDepart; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
