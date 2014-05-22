/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles.musique;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author MoMo
 */
@Entity
public class Artiste implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;   
    
    @OneToMany
    private Set<Morceau> morceaux;

    public Artiste() {
    }

    public Artiste(String nom) {
        this.nom = nom;
        this.morceaux = new HashSet<Morceau>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Morceau> getMorceaux() {
        return morceaux;
    }

    public void setMorceaux(Morceau morceau) {
        this.morceaux.add(morceau);
    }
}
