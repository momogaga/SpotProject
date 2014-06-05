/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import gestionnaires.GestionnaireMusiques;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import gestionnaires.GestionnaireUtilisateurs;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Web application lifecycle listener.
 *
 * @author MoMo
 */
public class CreationBD implements ServletContextListener {

    @EJB
    GestionnaireUtilisateurs gu;
    @EJB
    GestionnaireMusiques gm;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Déploiement terminé, BD Crée");
        gu.creerUtilisateursDeTest();
        //gm.creerMusiqueTest();
        
        try {
            String path = sce.getServletContext().getRealPath("/resources/data/liste.txt");
            gm.parse(path);
        } catch (IOException ex) {
            Logger.getLogger(CreationBD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
