/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnaires;

import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modeles.musique.Artiste;
import modeles.musique.Instrument;
import modeles.musique.Morceau;
import modeles.musique.Style;

/**
 *
 * @author MoMo
 */
@Stateless
public class GestionnaireMusiques {

    @PersistenceContext
    private EntityManager em;

    public void creerMorceauDeTest() {
        creerUnMorceau("Get Lucky", "2013");
    }

    public Morceau creerUnMorceau(String titre, String annee) {
        Morceau m = new Morceau(titre, annee);
   
        Artiste a = new Artiste("Daft Punk");
       
        Style s = new Style("Electro");
        
        m.setArtiste(a);
        m.setStyle(s);
         em.persist(a);
        em.persist(s);
        em.persist(m);
        return m;
    }

    public Collection<Artiste> chercherParArtiste(String artiste) {
        Query q = em.createQuery("select u from Artiste u where u.artiste=:artiste");
        q.setParameter("artiste", artiste);
        return q.getResultList();
    }

}
