/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueomega.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tpbanqueomega.entity.CompteBancaire;
import mg.itu.tpbanqueomega.service.GestionnaireCompte;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;


/**
 *
 * @author Dina
 */
@Named(value = "mouvements")
@ViewScoped
public class Mouvements implements Serializable {

    /**
     * Creates a new instance of Mouvements
     */
    
    private Long id;
    private CompteBancaire compte;
    private String typeMouvement;
    
    @PositiveOrZero
    private int montant;

    public Mouvements() {
    }

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }


    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
    }
  
  /** 
     * Méthode validatrice pour le montant du mouvement. Remarque : La méthode 
     * doit toujours avoir cette signature. 
     * 
     * @param fc 
     * @param composant le composant JSF dans lequel on valide. 
     * @param valeur valeur à valider (le montant pour ce cas) 
     */ 

     public void validateSolde(FacesContext fc, UIComponent composant, Object valeur) { 
        UIInput composantTypeMouvement = (UIInput) composant.findComponent("typeMouvement"); 
        // Il faut savoir si c'est un retrait ou un dépôt. 
        // Sans entrer dans les détails, il faut parfois utiliser 
        // getSubmittedValue() à la place de getLocalValue. 
        // typeMouvement n'est pas encore mis tant que la validation n'est pas finie. 
        String valeurTypeMouvement = (String) composantTypeMouvement.getLocalValue(); 
        if (valeurTypeMouvement == null) { 
            // Pour le cas où l'utilisateur a soumis le formulaire sans indiquer le type du Mouvements, 
            // Le test valeurTypeMouvement.equals("retrait") ci-dessous génèrera une erreur car 
            // il est exécuté avant que JSF ne vérifie que l'utilisateur a bien choisi 
            // entre ajout et retrait (le choix est requis dans la page Mouvements.xhtml) 
            return; 
        } 

        if (valeurTypeMouvement.equals("retrait")) { 
            int retrait = (int) valeur; 
            if (compte.getSolde() < retrait) { 
                FacesMessage message 
                        = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                                "Le retrait doit être inférieur au solde du compte", 
                                "Le retrait doit être inférieur au solde du compte"); 
                throw new ValidatorException(message); 
            } 
        } 
    } 
    /** 

     * Enregistre le Mouvements, ajout ou retirer 

     */ 

    public String enregistrerMouvement() { 
        if (typeMouvement.equals("ajout")) { 
            gestionnaireCompte.deposer(compte, montant); 
        } else { 
            gestionnaireCompte.retirer(compte, montant); 
        } 
        Util.addFlashInfoMessage("Mouvement enregistré sur compte de " + compte.getNom()); 
        return "listeComptes?faces-redirect=true"; 
    } 
}
