/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnaires;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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

    public void creerUtilisateursDeTest() {
        Utilisateur root = creerUtilisateur("root", "root");
        Utilisateur admin = creerUtilisateur("admin", "admin");
        Utilisateur test = creerUtilisateur("test", "test");

        Abonnement we = new Abonnement("Week-end", 3, 2);

        Abonnement semaine = new Abonnement("Semaine", 5, 7);

        root.setAbonnement(we);
        em.merge(root);

        root.setAbonnement(semaine);
        em.merge(root);

    }

    public Utilisateur creerUtilisateur(String login, String password) {
        Utilisateur u = new Utilisateur(login, password);
        Abonnement gratuit = new Abonnement("Gratuit", 0, 0);
        em.persist(gratuit);
        u.setAbonnement(gratuit);
        em.persist(u);
        return u;
    }

    public Utilisateur chercherUnUtilisateurParLogin(String login) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
        q.setParameter("login", login);
        Utilisateur u = (Utilisateur) q.getSingleResult();
        return u;
    }

    public void modifierUtilisateur(String login, String nomAbonnement) {
        Utilisateur u = chercherUnUtilisateurParLogin(login);

        System.out.println("Login : " + u.getLogin() + "Pass : " + u.getPassword() + "Abo : " + nomAbonnement);
        Abonnement abonnement = null;

        switch (nomAbonnement) {
            case ("Week-end"):
                abonnement = new Abonnement(nomAbonnement, 3, 2);
                em.persist(abonnement);
                u.setAbonnement(abonnement);
                em.merge(u);
                break;
            case ("Semaine"):
                abonnement = new Abonnement(nomAbonnement, 5, 7);
                em.persist(abonnement);
                u.setAbonnement(abonnement);
                em.merge(u);
                break;
            case ("Mois"):
                abonnement = new Abonnement(nomAbonnement, 15, 30);
                em.persist(abonnement);
                u.setAbonnement(abonnement);
                em.merge(u);
                break;
            case ("An"):
                abonnement = new Abonnement(nomAbonnement, 45, 365);
                em.persist(abonnement);
                u.setAbonnement(abonnement);
                em.merge(u);
                break;
            case ("Vie"):
                abonnement = new Abonnement(nomAbonnement, 300, -1);
                em.persist(abonnement);
                u.setAbonnement(abonnement);
                em.merge(u);
                break;
            default:
                break;
        }
    }

    public void supprimeUnUtilisateur(int id) {

        Utilisateur u = em.find(Utilisateur.class, id);
        em.remove(u);

    }

    public Collection<Utilisateur> getAllUsers() {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select u from Utilisateur u");
        return q.getResultList();
    }

    public Collection<Utilisateur> getAllUsers(int decalage, int elements) {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select u from Utilisateur u");

        q.setFirstResult(decalage);
        q.setMaxResults(elements);

        return q.getResultList();
    }

    public int getElements() {
        Query q = em.createQuery("select u from Utilisateur u");
        return q.getResultList().size();
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
}
