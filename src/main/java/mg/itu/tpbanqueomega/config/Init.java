/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanqueomega.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import mg.itu.tpbanqueomega.entity.CompteBancaire;
import mg.itu.tpbanqueomega.service.GestionnaireCompte;
import java.util.logging.Logger;

/**
 *
 * @author Dina
 */
@ApplicationScoped
public class Init {
    private static final Logger LOGGER = Logger.getLogger(Init.class.getName());
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    
    public Init() {
        
    }
    
    @PostConstruct
    public void init(
        @Observes
        @Initialized(ApplicationScoped.class)
        ServletContext context) {
        LOGGER.info("Initialisation de la création des comptes...");
        gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
        LOGGER.info("Compte de John Lennon créé avec succès.");
        gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
        LOGGER.info("Compte de Paul McCartney créé avec succès.");
        gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
        LOGGER.info("Compte de Ringo Starr créé avec succès.");
        gestionnaireCompte.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
        LOGGER.info("Compte de George Harrison créé avec succès.");
        
        LOGGER.info("Initialisation de la création des comptes terminée.");
    
    }
    
}
