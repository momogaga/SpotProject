/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles.utilisateur;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Bastien
 */
@Entity
public class Abonnement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private double prix;
//   private Date dateDebut;
//   private Date dateFin;

    public Abonnement() {
    }

    public Abonnement(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
//        this.dateDebut = dateDebut;
//        this.dateFin = dateFin;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

//    public Date getDateDebut() {
//        return dateDebut;
//    }
//
//    public Date getDateFin() {
//        return dateFin;
//    }
}
