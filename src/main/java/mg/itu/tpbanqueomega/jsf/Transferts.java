/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueomega.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import mg.itu.tpbanqueomega.entity.CompteBancaire;
import mg.itu.tpbanqueomega.service.GestionnaireCompte;

/**
 *
 * @author Dina
 */
@Named(value = "transferts")
@RequestScoped
public class Transferts {
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    private Long idSource;
    private Long idDestination;
    private int montant;

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }
    
    public Long getIdDestination() {
        return idDestination;
    }
    
    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }
    
    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Creates a new instance of transfert
     */
    public Transferts() {
    }
    
    public String soumettre() {
        
        boolean erreur = false;
        
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        if (source == null) {
            Util.messageErreur("Compte source introuvable", "Le compte source spécifié n'existe pas.", "form:source");
            erreur = true;
        }
            
        if (destination == null){
            Util.messageErreur("Compte destination introuvable", "Le compte destination spécifié n'existe pas.", "form:destination");
            erreur = true;
        } 
          
        if (source != null && source.getSolde() < montant) {
            Util.messageErreur("Solde insuffisant", "Le solde du compte source est insuffisant pour effectuer le transfert.", "form:montant");
            erreur = true;
        }
        
        if (erreur) { 
          return null;
        }
        
        gestionnaireCompte.transferer(idSource, idDestination, montant);
        
        if(source != null && destination != null) {
            String compteSource = source.getNom();
            String compteDestination = destination.getNom();
            
            String messageDetail = "Transfert réussi de " + compteSource + " à " + compteDestination + " pour un montant de " + montant;
            String messageResume = "Transfert réussi";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageDetail, messageResume);
            Util.addFlashMessage(message);
        }
       
        return "listeComptes?faces-redirect=true";
    }
    
}
