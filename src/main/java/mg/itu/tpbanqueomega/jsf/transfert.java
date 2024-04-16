/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueomega.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.itu.tpbanqueomega.entity.CompteBancaire;
import mg.itu.tpbanqueomega.service.GestionnaireCompte;

/**
 *
 * @author Dina
 */
@Named(value = "transfert")
@RequestScoped
public class transfert {
    
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
    public transfert() {
    }
    
    public String soumettre() {
        gestionnaireCompte.transferer(idSource, idDestination, montant);
        return "listeComptes?transfert="+ idSource + idDestination + montant + "&amp;faces-redirect=true";
    }
    
}
