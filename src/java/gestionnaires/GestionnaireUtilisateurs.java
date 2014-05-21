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
        creerUnUtilisateur("root", "root");
        creerUnUtilisateur("admin", "admin");
        creerUnUtilisateur("test", "test");

        modifierAbonnement("Semaine", "test");        
    }

    public Utilisateur creerUnUtilisateur(String login, String password) {
        Utilisateur u = new Utilisateur(login, password);

        Abonnement gratuit = new Abonnement("Gratuit", 0);
        u.setAbonnement(gratuit);
        em.persist(gratuit);

        em.persist(u);
        return u;
    }

    public Collection<Utilisateur> chercherParLogin(String login) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
        q.setParameter("login", login);
        return q.getResultList();
    }

    public Utilisateur chercherUnUtilisateurParLogin(String login) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
        q.setParameter("login", login);
        Utilisateur u = (Utilisateur) q.getSingleResult();
        return u;
    }

    public void modifieUnUtilisateur(String nom, String prenom, String login, String password) {
        Utilisateur u = chercherUnUtilisateurParLogin(login);
        u.setLogin(login);
        String CryptedPass = this.encrypt(password);
        u.setPassword(CryptedPass);
    }

    public void modifierAbonnement(String nomAbonnement, String login) {
        Utilisateur u = chercherUnUtilisateurParLogin(login);
        Abonnement abo = null;

        switch (nomAbonnement) {
            case ("Week-end"):
                abo = new Abonnement(nomAbonnement, 3);
                u.setAbonnement(abo);
                em.persist(abo);
                break;
            case ("Semaine"):
                abo = new Abonnement(nomAbonnement, 5);
                u.setAbonnement(abo);
                em.persist(abo);
                break;
            case ("Mois"):
                abo = new Abonnement(nomAbonnement, 15);
                u.setAbonnement(abo);
                em.persist(abo);
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
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
