/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnaires;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modeles.utilisateur.Abonnement;
import modeles.utilisateur.Utilisateur;

/**
 *
 * @author MoMo
 */
@Stateless
public class GestionnaireUtilisateurs {

    // Ici injection de code : on n'initialise pas. L'entity manager sera créé
    // à partir du contenu de persistence.xml
    @PersistenceContext
    private EntityManager em;

    public void initAbonnement() {
        Abonnement gratuit = new Abonnement("Gratuit", 3, 2);
        em.persist(gratuit);
        Abonnement we = new Abonnement("Week-end", 3, 2);
        em.persist(we);
        Abonnement semaine = new Abonnement("Semaine", 5, 7);
        em.persist(semaine);
        Abonnement mois = new Abonnement("Mois", 10, 30);
        em.persist(mois);
        Abonnement an = new Abonnement("An", 45, 365);
        em.persist(an);
        Abonnement vie = new Abonnement("Vie", 300, -1);
        em.persist(vie);
    }

    public void creerUtilisateursDeTest() {
        initAbonnement();

        Utilisateur root = creerUtilisateur("root", "root");
        Utilisateur admin = creerUtilisateur("admin", "admin");
    }

    public Utilisateur creerUtilisateur(String login, String password) {
        Utilisateur u = new Utilisateur(login, password);
        Abonnement a = em.find(Abonnement.class, 1);
        u.setAbonnement(a);
        a.addUtilisateur(u);
        em.persist(u);
        return u;
    }

    public Utilisateur chercherUnUtilisateurParLogin(String login) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
        q.setParameter("login", login);
        Utilisateur u = (Utilisateur) q.getSingleResult();
        return u;
    }

    public void modifierUtilisateur(String login, int id) {
        Utilisateur u = chercherUnUtilisateurParLogin(login);

        Abonnement a = em.find(Abonnement.class, id);
        u.setAbonnement(a);
        a.addUtilisateur(u);
    }

    public void supprimeUnUtilisateur(int id) {
        Utilisateur u = em.find(Utilisateur.class, id);
        em.remove(u);
    }

    public Boolean isUser(String login, String password) {
        String passCrypte = this.encrypt(password);
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login and u.password=:passCrypte");
        q.setParameter("passCrypte", passCrypte);
        q.setParameter("login", login);
        if (q.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public String encrypt(String password) {
        String crypte = "";
        for (int i = 0; i < password.length(); i++) {
            int c = password.charAt(i) ^ 48;
            crypte = crypte + (char) c;
        }
        return crypte;
    }

    public Collection<Utilisateur> afficherUtilisateur() {
        Query q = em.createQuery("select u from Utilisateur u");
        return q.getResultList();
    }
}
