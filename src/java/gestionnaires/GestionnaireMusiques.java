/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnaires;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;
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

    public Collection<Morceau> getAllMusic() {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select m from Morceau m order by m.titre asc");
        return q.getResultList();
    }

    public Collection<Morceau> getAllMusic(int decalage, int elements) {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select m from Morceau m order by m.titre asc");

        q.setFirstResult(decalage);
        q.setMaxResults(elements);

        return q.getResultList();
    }

    public int getElements() {
        Query q = em.createQuery("select m from Morceau m");
        return q.getResultList().size();
    }

    public void parse() throws IOException {
        Artiste a = null;
        Morceau m = null;
        Piste p = null;

        InputStreamReader lecture = new InputStreamReader(new FileInputStream("C:\\Users\\Bastien\\Documents\\NetBeansProjects\\SpotProject\\web\\resources\\data\\liste.txt"));
        BufferedReader buff = new BufferedReader(lecture);
        String ligne;

        Pattern tiretPattern = Pattern.compile("(\\s-\\s)");

        while ((ligne = buff.readLine()) != null) {

            if (!Pattern.matches("^.*\\.[0-9a-z]+$", ligne) && !Pattern.matches("^\\s*", ligne)) {

                String[] items = tiretPattern.split(ligne);

                a = new Artiste(items[0]);

                m = new Morceau(items[1].replace(":", ""), "2014", a);
                a.addMorceau(m);

                em.persist(a);
                em.persist(m);
            } else if (!Pattern.matches("^\\s*", ligne) && (ligne.contains(".mp3") || ligne.contains(".ogg") || ligne.contains(".mogg"))) {
                p = new Piste(ligne, 5, m);
                m.addPiste(p);
                em.persist(p);
            }
        }
    }
}
