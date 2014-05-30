/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles.achat;

import java.io.Serializable;
import java.text.NumberFormat;
import modeles.musique.Morceau;

/**
 *
 * @author MoMo
 */
public class Item implements Serializable {

    private Morceau morceau;
    private int quantite;

    public Item() {
    }

    public Morceau getMorceau() {
        return morceau;
    }

    public void setMorceau(Morceau morceau) {
        this.morceau = morceau;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getTotal() {
        double total = Morceau.getPrice() * quantite;
        return total;
    }

    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(this.getTotal());
    }
}
