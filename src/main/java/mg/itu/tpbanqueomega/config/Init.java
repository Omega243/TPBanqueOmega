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

/**
 *
 * @author Dina
 */
@ApplicationScoped
public class Init {
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    public Init() {
        
    }
    
    @PostConstruct
    public void init(
        @Observes
        @Initialized(ApplicationScoped.class)
        ServletContext context) {
        
        if (gestionnaireCompte.getAllComptes().isEmpty()) {
            CompteBancaire[] comptes = new CompteBancaire[4];
            comptes[0] = new CompteBancaire("John Lennon", 150000);
            comptes[1] = new CompteBancaire("Paul McCartney", 950000);
            comptes[2] = new CompteBancaire("Ringo Starr", 20000);
            comptes[3] = new CompteBancaire("Georges Harrisson", 100000);
            
            for (CompteBancaire c : comptes) {
                gestionnaireCompte.creerCompte(c);
            }
        }
    
    }
    
}
