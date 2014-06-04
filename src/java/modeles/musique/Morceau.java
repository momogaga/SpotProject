/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles.musique;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import modeles.utilisateur.Utilisateur;

/**
 *
 * @author Bastien
 */
@Entity
public class Morceau implements Serializable {

    public static int getPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String titre;
    private String annee;
    private double prix;

    @OneToOne(cascade = {CascadeType.ALL})
    private Artiste artiste;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Piste> pistes;

    public Morceau() {
    }

    public Morceau(String titre, String annee, double prix, Artiste artiste) {
        this.titre = titre;
        this.annee = annee;
        this.artiste = artiste;
        this.prix = prix;
        this.pistes = new HashSet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Set<Piste> getPistes() {
        return pistes;
    }

    public void setPistes(Set<Piste> pistes) {
        this.pistes = pistes;
    }

    public void addPiste(Piste p) {
        this.pistes.add(p);
    }

    @Override
    public String toString() {
        return "Morceau : " + titre + "," + annee;
    }
}
