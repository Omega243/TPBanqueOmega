/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueomega.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanqueomega.entity.CompteBancaire;
import mg.itu.tpbanqueomega.entity.OperationBancaire;
import mg.itu.tpbanqueomega.service.GestionnaireCompte;


/**
 * Backing bean pour la page operations.xhtml.
 * @author Dina
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {
    
    private Long id;
    private CompteBancaire compte;
    private List<OperationBancaire> operations;
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
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
    
    public List<OperationBancaire> getOperations() {
        return compte.getOperations();
    }

    /**
     * Creates a new instance of ListeComptes
     */
    public Operations() {
    }
    
    

    
}
