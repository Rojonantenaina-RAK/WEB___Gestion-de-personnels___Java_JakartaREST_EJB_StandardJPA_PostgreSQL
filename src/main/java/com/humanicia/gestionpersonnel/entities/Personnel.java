package com.humanicia.gestionpersonnel.entities;

import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;
import java.util.List;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "personnel")
public class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricule")
    private Integer matricule;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(columnDefinition = "TEXT")
    private String adresse;

    @Column(length = 20)
    private String telephone;

    @Column(length = 100)
    private String email;

    @Column(name = "date_embauche", nullable = false)
    private LocalDate dateEmbauche;

    @Column(name = "salaire", precision = 10, scale = 2)
    private BigDecimal salaire;

    @ManyToOne
    @JoinColumn(name = "id_service", referencedColumnName = "id_service")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "id_poste", referencedColumnName = "id_poste")
    private Poste poste;

    @OneToMany(mappedBy = "personnel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonbTransient
    private List<Presence> presences;

    // Getters / Setters
    public Integer getMatricule() { return matricule; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(LocalDate dateEmbauche) { this.dateEmbauche = dateEmbauche; }
    public BigDecimal getSalaire() { return salaire; }
    public void setSalaire(BigDecimal salaire) { this.salaire = salaire; }
    public Service getService() { return service; }
    public void setService(Service service) { this.service = service; }
    public Poste getPoste() { return poste; }
    public void setPoste(Poste poste) { this.poste = poste; }
    public List<Presence> getPresences() { return presences; }
    public void setPresences(List<Presence> presences) { this.presences = presences; }
}
