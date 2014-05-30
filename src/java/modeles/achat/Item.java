package modeles.achat;

import java.io.Serializable;
import modeles.musique.Morceau;

public class Item implements Serializable
{
    private Morceau morceau;
    private int quantity;
    
    public Item() {}
    
    public void setMorceau(Morceau morceau)
    {
        this.morceau = morceau;
    }

    public Morceau getMorceau()
    { 
        return morceau;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public int getQuantity()
    { 
        return quantity; 
    }
}