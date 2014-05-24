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
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modeles.musique.Artiste;
import modeles.musique.Morceau;
import modeles.musique.Piste;
import modeles.utilisateur.Utilisateur;

/**
 *
 * @author MoMo
 */
@Stateless
public class GestionnaireMusiques {

    @PersistenceContext
    private EntityManager em;

    public void creerMusiqueTest() {

        Artiste a = new Artiste("ACDC");
        Artiste ab = new Artiste("Mors");
        
        Morceau m = new Morceau("High", "2014", a);
        Morceau mr = new Morceau("Well", "2014", ab);
        
        Piste p1 = new Piste("Bass", 5, m);
        Piste p2 = new Piste("Vocals 2 Main", 3, m);
        Piste p3 = new Piste("Drums 4 snare", 5, m);
        Piste p4 = new Piste("Misc Bells", 2, mr);
        Piste p5 = new Piste("Bass2", 5, mr);

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.persist(p5);

        a.addMorceau(m);
        ab.addMorceau(mr);
        m.addPiste(p1);
        m.addPiste(p2);
        m.addPiste(p3);
        mr.addPiste(p4);
        mr.addPiste(p5);

        em.persist(m);
        em.persist(mr);
        em.persist(a);
        em.persist(ab);
    }

    public Artiste creerArtiste(String nom) {
        Artiste a = new Artiste(nom);
        em.persist(a);

        return a;
    }

    public Collection<Morceau> getAllMusic() {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select m from Morceau m");
        return q.getResultList();
    }

    public Collection<Morceau> getAllMusic(int decalage, int elements) {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select m from Morceau m");

        q.setFirstResult(decalage);
        q.setMaxResults(elements);

        return q.getResultList();
    }

    public int getElements() {
        Query q = em.createQuery("select m from Morceau m");
        return q.getResultList().size();
    }
}
