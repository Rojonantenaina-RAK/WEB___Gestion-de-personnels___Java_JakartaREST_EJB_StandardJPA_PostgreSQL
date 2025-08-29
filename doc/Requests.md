# Personnel
### GET
```
curl -X GET http://localhost:8080/gestion-personnel/api/personnels \
  -H "Accept: application/json"
```
```
curl -X GET http://localhost:8080/gestion-personnel/api/personnels/1 \
  -H "Accept: application/json"
```
### POST
```
curl -X POST http://localhost:8080/gestion-personnel/api/personnels \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Rakoto",
    "prenom": "Jean",
    "adresse": "Antananarivo",
    "telephone": "0341234567",
    "email": "jean.rakoto@example.com",
    "dateEmbauche": "2023-05-10",
    "salaire": 750000,
    "idService": 1,
    "idPoste": 2
  }'
```
### PUT
```
curl -X PUT http://localhost:8080/gestion-personnel/api/personnels/1 \
  -H "Content-Type: application/json" \
  -d '{
    "matricule": 1,
    "nom": "RANDRIANANTENAINA",
    "prenom": "Armel Mis à jour",
    "adresse": "Antananarivo",
    "telephone": "0341234567",
    "email": "armel.updated@gmail.com",
    "dateEmbauche": "2023-05-10",
    "salaire": 950000,
    "service": { "idService": 1 },
    "poste": { "idPoste": 1 }
  }'
```
### DELETE
```
curl -X DELETE http://localhost:8080/gestion-personnel/api/personnels/1
```


# Contrat
### GET
```
curl -X GET http://localhost:8080/gestion-personnel/api/services \
  -H "Accept: application/json"
```
```
curl -X GET http://localhost:8080/gestion-personnel/api/services/1 \
  -H "Accept: application/json"
```
### POST
```
curl -X POST http://localhost:8080/gestion-personnel/api/services \
  -H "Content-Type: application/json" \
  -d '{
    "nomService": "Informatique",
    "localisation": "Antananarivo"
  }'
```
### PUT
```
curl -X PUT http://localhost:8080/gestion-personnel/api/services/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nomService": "Ressources Humaines",
    "localisation": "Mahajanga"
  }'
```
### DELETE
```
curl -X DELETE http://localhost:8080/gestion-personnel/api/services/1
```


# Poste
### GET
```
curl -X GET http://localhost:8080/gestion-personnel/api/postes \
  -H "Accept: application/json"
```
```
curl -X GET http://localhost:8080/gestion-personnel/api/postes/1 \
  -H "Accept: application/json"
```
### POST
```
curl -X POST http://localhost:8080/gestion-personnel/api/postes \
  -H "Content-Type: application/json" \
  -d '{
    "libelle": "Développeur Backend",
    "description": "Responsable du développement des APIs et de la logique métier."
  }'
```
### PUT
```
curl -X PUT http://localhost:8080/gestion-personnel/api/postes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "libelle": "Développeur Fullstack",
    "description": "Développement frontend et backend, maintenance et optimisation."
  }'
```
### DELETE
```
curl -X DELETE http://localhost:8080/gestion-personnel/api/postes/1
```

# Service
### GET
```
curl -X GET http://localhost:8080/gestion-personnel/api/services \
  -H "Accept: application/json"
```
```
curl -X GET http://localhost:8080/gestion-personnel/api/services/1 \
  -H "Accept: application/json"
```
### POST
```
curl -X POST http://localhost:8080/gestion-personnel/api/services \
  -H "Content-Type: application/json" \
  -d '{
    "nomService": "Informatique",
    "localisation": "Bureau 3ème étage"
  }'
```
### PUT
```
curl -X PUT http://localhost:8080/gestion-personnel/api/services/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nomService": "Ressources Humaines",
    "localisation": "Bâtiment A"
  }'
```
### DELETE
```
curl -X DELETE http://localhost:8080/gestion-personnel/api/services/1
```


# Presence
### GET
```
curl -X GET http://localhost:8080/gestion-personnel/api/presences
```
```
curl -X GET http://localhost:8080/gestion-personnel/api/presences/1
```
### POST
```
curl -X POST http://localhost:8080/gestion-personnel/api/presences \
  -H "Content-Type: application/json" \
  -d '{
    "datePresence": "2024-09-05",
    "heureArrivee": "08:00:00",
    "heureDepart": "16:30:00",
    "statut": "Présent",
    "matricule": 1
  }'
```
### PUT
```
curl -X PUT http://localhost:8080/gestion-personnel/api/presences/1 \
  -H "Content-Type: application/json" \
  -d '{
    "idPresence": 1,
    "datePresence": "2025-08-25",
    "heureArrivee": "08:15:00",
    "heureDepart": "17:05:00",
    "statut": "Présent",
    "personnel": { "matricule": 1 }
  }'
```
### DELETE
```
curl -X DELETE http://localhost:8080/gestion-personnel/api/presences/1
```


# Projet
### GET
```
curl -X GET http://localhost:8080/gestion-personnel/api/projets
```
```
curl -X GET http://localhost:8080/gestion-personnel/api/projets/1
```
### POST
```
curl -X POST http://localhost:8080/gestion-personnel/api/projets \
  -H "Content-Type: application/json" \
  -d '{
    "nomProjet": "Application RH",
    "objectif": "Développer un système de gestion du personnel",
    "dateDebut": "2023-10-01",
    "dateFin": "2024-03-31"
  }'
```
### PUT
```
curl -X PUT http://localhost:8080/gestion-personnel/api/projets/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nomProjet": "Application RH v2",
    "objectif": "Ajout de la gestion des congés",
    "dateDebut": "2023-10-01",
    "dateFin": "2024-06-30"
  }'
```
### DELETE
```
curl -X DELETE http://localhost:8080/gestion-personnel/api/projets/1
```


# Tache
### GET
```
curl -X GET http://localhost:8080/gestion-personnel/api/taches
```
```
curl -X GET http://localhost:8080/gestion-personnel/api/taches/1
```
### POST
```
curl -X POST http://localhost:8080/gestion-personnel/api/taches \
  -H "Content-Type: application/json" \
  -d '{
    "libelle": "Développement Backend",
    "description": "Implémenter les API REST pour la gestion du personnel",
    "dateDebut": "2024-09-01",
    "dateFin": "2024-09-15",
    "statut": "En cours",
    "matricule": 1,
    "idProjet": 1
  }'
```
### PUT
```
curl -X PUT http://localhost:8080/gestion-personnel/api/taches/1 \
  -H "Content-Type: application/json" \
  -d '{
    "idTache": 1,
    "libelle": "Développement API REST",
    "description": "Améliorer les endpoints pour la gestion RH",
    "dateDebut": "2025-02-01",
    "dateFin": "2025-04-15",
    "statut": "Terminé",
    "personnel": { "matricule": 1 },
    "projet": { "idProjet": 1 }
  }'
```
### DELETE
```
curl -X DELETE http://localhost:8080/gestion-personnel/api/taches/1
```