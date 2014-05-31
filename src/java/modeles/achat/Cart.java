package modeles.achat;

import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.Stateful;

@Stateful
public class Cart implements Serializable {

    private ArrayList<Item> items;

    public Cart() {
        items = new ArrayList<Item>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(Item item) {
        int code = item.getMorceau().getId();
        int quantity = item.getQuantity();
        for (int i = 0; i < items.size(); i++) {
            Item lineItem = items.get(i);
            if (lineItem.getMorceau().getId() == code) {
                lineItem.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(Item item) {
        int code = item.getMorceau().getId();
        for (int i = 0; i < items.size(); i++) {
            Item lineItem = items.get(i);
            if (lineItem.getMorceau().getId() == code) {
                items.remove(i);
                return;
            }
        }
    }
}
