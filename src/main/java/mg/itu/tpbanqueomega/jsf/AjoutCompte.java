/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueomega.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.tpbanqueomega.entity.CompteBancaire;
import mg.itu.tpbanqueomega.service.GestionnaireCompte;

/**
 *
 * @author Dina
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    private String nom;
    
    @PositiveOrZero
    private int solde;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    @Transactional
    public String ajouter(){
        boolean erreur = false;
        if (nom == null || nom.trim().isEmpty()) {
            Util.messageErreur("Le champ nom doit être rempli", "Le champ nom doit être rempli", "form:nom");
            erreur = true;
        }

        if (!erreur) {
            gestionnaireCompte.creerCompte(new CompteBancaire(nom, solde));
            Util.addFlashInfoMessage("Compte de " + nom + " a été créé avec succès avec un montant de " + solde);
            return "listeComptes?faces-redirect=true";
        } else {
            return null;
        }
    }
}
