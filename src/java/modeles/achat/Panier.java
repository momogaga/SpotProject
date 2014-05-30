/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles.achat;

import java.io.Serializable;
import java.util.ArrayList;

public class Panier implements Serializable {

    private ArrayList<Item> items;

    public Panier() {
        items = new ArrayList<Item>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(Item item) {
        String code = item.getMorceau().getTitre();
        int quantite = item.getQuantite();
        for (int i = 0; i < items.size(); i++) {
            Item lineitem = items.get(i);
            if (lineitem.getMorceau().getTitre().equals(code)) {
                lineitem.setQuantite(quantite);
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(Item item) {
        String code = item.getMorceau().getTitre();
        for (int i = 0; i < items.size(); i++) {
            Item lineItem = items.get(i);
            if (lineItem.getMorceau().getTitre().equals(code)) {
                items.remove(i);
                return;
            }
        }
    }
}
