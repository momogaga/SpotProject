/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles.musique;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Bastien
 */
@Entity
public class Piste implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nom;
    private int difficulte;

    @OneToOne(cascade = {CascadeType.ALL})
    private Morceau morceau;

    public Piste() {
    }

    public Piste(String nom, int difficulte, Morceau morceau) {
        this.nom = nom;
        this.difficulte = difficulte;
        this.morceau = morceau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    @Override
    public String toString() {
        return "Piste : " + nom + "," + difficulte + "," + morceau.toString();
    }
}
