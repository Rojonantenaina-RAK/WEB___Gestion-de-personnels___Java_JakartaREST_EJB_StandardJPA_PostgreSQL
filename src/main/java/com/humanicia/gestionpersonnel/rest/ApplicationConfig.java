package com.humanicia.gestionpersonnel.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api") // tous les services REST seront sous /api
public class ApplicationConfig extends Application {
    // vide, juste pour activer JAX-RS
}
