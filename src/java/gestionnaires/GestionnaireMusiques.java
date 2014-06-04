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

    public Collection<Morceau> getMusicBy(int decalage, int elements, String type, String search) {
        Query q = em.createQuery("select m from Morceau m");

        if (type.equals("Titre")) {
            q = em.createQuery("select m from Morceau m where lower(m.titre) LIKE lower(:titre) order by m.titre asc");
            q.setParameter("titre", "%" + search + "%");
        }
        if (type.equals("Artiste")) {
            q = em.createQuery("select m from Morceau m where lower(m.artiste.nom) LIKE lower(:artiste) order by m.titre asc");
            q.setParameter("artiste", "%" + search + "%");
        }

        q.setFirstResult(decalage);
        q.setMaxResults(elements);

        return q.getResultList();
    }

    public int getElements() {
        Query q = em.createQuery("select m from Morceau m");
        return q.getResultList().size();
    }

    public Morceau getMorceau(int id) {
        Query q = em.createQuery("select m from Morceau m where m.id=:id");
        q.setParameter("id", id);
        Morceau m = (Morceau) q.getSingleResult();
        return m;
    }

    public void parse() throws IOException {
        Artiste a = null;
        Morceau m = null;
        Piste p = null;

        InputStreamReader lecture = new InputStreamReader(new FileInputStream("C:\\Users\\GAIECH\\Documents\\NetBeansProjects\\SpotProject\\web\\resources\\data\\liste.txt"));
//        InputStreamReader lecture = new InputStreamReader(new FileInputStream("C:\\Users\\Bastien\\Documents\\NetBeansProjects\\SpotProject\\web\\resources\\data\\liste.txt"));
        BufferedReader buff = new BufferedReader(lecture);
        String ligne;

        Pattern tiretPattern = Pattern.compile("(\\s-\\s)");

        while ((ligne = buff.readLine()) != null) {

            if (!Pattern.matches("^.*\\.[0-9a-z]+$", ligne) && !Pattern.matches("^\\s*", ligne)) {

                String[] items = tiretPattern.split(ligne);

                a = new Artiste(items[0]);

                m = new Morceau(items[1].replace(":", ""), "2014", 1.80, a);
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
