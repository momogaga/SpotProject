/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles.utilisateur;

/**
 *
 * @author Bastien
 */
public enum TypeAbonnement {

    GRA("Gratuit"),
    WE("Week-end"),
    SEM("Semaine"),
    MOIS("Mois"),
    AN("An"),
    VIE("Vie");

    private String name = "";

    private TypeAbonnement(String name) {
        this.name = name;
    }

}
