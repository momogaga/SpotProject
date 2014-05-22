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
import modeles.musique.Piste;
import modeles.musique.Morceau;

/**
 *
 * @author MoMo
 */
@Stateless
public class GestionnaireMusiques {

    @PersistenceContext
    private EntityManager em;

    public void creerMusiqueTest() {
        Artiste a = new Artiste("Artiste");

        Morceau m = new Morceau("Titre", "2014");

        a.setMorceaux(m);

        Piste p = new Piste("nom", "facile");

        em.persist(a);
        em.persist(m);
        em.persist(p);

    }

    public Collection<Artiste> chercherParArtiste(String artiste) {
        Query q = em.createQuery("select u from Artiste u where u.artiste=:artiste");
        q.setParameter("artiste", artiste);
        return q.getResultList();
    }

    public Artiste creerArtiste(String nom) {
        Artiste a = new Artiste(nom);
        em.persist(a);

        return a;
    }

    public Piste creerPiste(String nom, String difficulte) {
        Piste p = new Piste(nom, difficulte);
        em.persist(p);

        return p;
    }

}
