/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles.utilisateur;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


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
    private int duree;
    
    public Abonnement() {
    }

    public Abonnement(String nom, double prix, int duree) {
        this.nom = nom;
        this.prix = prix;
        this.duree = duree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
       
    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public int getDuree() {
        return duree;
    } 
}
