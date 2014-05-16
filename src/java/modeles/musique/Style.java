/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles.musique;

/**
 *
 * @author Bastien
 */
public enum Style {

    ROCK("Rock"),
    REGGAE("Reggae"),
    HOUSE("House");

    private String name = "";

    Style(String name) {
        this.name = name;
    }
}
