/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanqueomega.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.itu.tpbanqueomega.entity.CompteBancaire;

/**
 *
 * @author Dina
 */
@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="dinaa",              
    password="Win@2437&", 
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true",
      "driverClass=com.mysql.cj.jdbc.Driver"
    }
)
@ApplicationScoped
public class GestionnaireCompte {
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;
    
    public GestionnaireCompte(){
    
    }
    
    public List<CompteBancaire> getAllComptes() {
       Query query = em.createNamedQuery("CompteBancaire.findAll");
       return query.getResultList();
    }
    
    public Long nbComptes() {
        Query query = em.createQuery("CompteBancaire.count");
        return (Long) query.getSingleResult();
    }
    
    @Transactional
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }
    
    public CompteBancaire findById(Long id) {
        return em.find(CompteBancaire.class, id);
    }
    
    @Transactional
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }
    
    @Transactional
    public void deposer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.deposer(montant);
        update(compteBancaire);
    }
    
    @Transactional
    public void retirer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.retirer(montant);
        update(compteBancaire);
    }
    
    @Transactional
    public void transferer(Long idSource, Long idDestination, int montant) {
        CompteBancaire source = this.findById(idSource);
        CompteBancaire destination = this.findById(idDestination);
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    @Transactional
    public void supprimerCompte(CompteBancaire compte) {
        em.remove(em.merge(compte));
    }
    
}
