/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles.utilisateur;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import modeles.musique.Morceau;

/**
 *
 * @author MoMo
 */
@Entity
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String password;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebutAbo;

    @OneToOne(cascade = {CascadeType.ALL})
    private Abonnement abonnement;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<Morceau> morceauxAchetes;
    
    public Utilisateur() {
    }

    public Utilisateur(String login, String password) {
        this.login = login;
        this.password = this.encrypt(password);
        this.abonnement = new Abonnement();
        Calendar cal = Calendar.getInstance();

        this.dateDebutAbo = cal.getTime();
        this.morceauxAchetes = new HashSet();
    }

    public void setDateDebutAbo(Date dateDebutAbo) {
        this.dateDebutAbo = dateDebutAbo;
    }

    public Date getDateDebutAbo() {
        return dateDebutAbo;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Morceau> getMorceauxAchetes() {
        return morceauxAchetes;
    }

    public void setMorceauxAchetes(Set<Morceau> morceauxAchetes) {
        this.morceauxAchetes = morceauxAchetes;
    }
    
    public void addMorceauAchetes(Morceau m){
        this.morceauxAchetes.add(m);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String encrypt(String password) {
        String crypte = "";
        for (int i = 0; i < password.length(); i++) {
            int c = password.charAt(i) ^ 48;
            crypte = crypte + (char) c;
        }
        return crypte;
    }

    @Override
    public String toString() {
        return "utilisateurs.modeles.Utilisateur[ id=" + id + " ]";
    }

}
