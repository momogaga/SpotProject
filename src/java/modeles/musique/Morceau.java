/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles.musique;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Bastien
 */
public class Morceau {

    protected String titre;
    protected String artiste;
    protected Style style;
    protected Date annee;
    protected Collection<Piste> pistes;

}
