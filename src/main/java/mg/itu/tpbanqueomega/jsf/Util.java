/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanqueomega.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;

/**
 * Classe utilitaire pour JSF.
 * Pour les messages, avec ou sans redirection.
 */

/**
 *
 * @author Dina
 */
public class Util {
    
    public static void errorMessage(String messageDetail, String messageResume,
                FacesMessage.Severity severite, String id) {
        FacesMessage errorMessage =
                new FacesMessage(severite, messageResume, messageDetail);
        FacesContext.getCurrentInstance().addMessage(id, errorMessage);
    }

    public void addFlashMessage(FacesMessage message) {
        FacesContext facesContext =
        FacesContext.getCurrentInstance();
        Flash flash =
        facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        facesContext.addMessage(null, message);
    }
    
}
