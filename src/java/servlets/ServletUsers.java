/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import gestionnaires.GestionnaireUtilisateurs;
import modeles.utilisateur.Utilisateur;

/**
 *
 * @author MoMo
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {

    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void affiche() {

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pratique pour décider de l'action à faire  
        String action = request.getParameter("action");
        String forwardTo = "";
        String message = "";

        String login = request.getParameter("login");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String password = request.getParameter("password");

        int page = 1;
        int elementsParPage = 10;

        if (action != null) {
            if (action.equals("listerLeUtilisateur")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLeUtilisateur";
                message = "Affiche un utilisateurs";
            } else if (action.equals("listerLesUtilisateurs")) {
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }

                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers((page - 1) * elementsParPage,
                        elementsParPage);

                int elements = gestionnaireUtilisateurs.getElements();
                int numPage = (int) Math.ceil(elements * 1.0 / elementsParPage);

                request.setAttribute("listeDesUsers", liste);
                request.setAttribute("noOfPages", numPage);
                request.setAttribute("currentPage", page);

                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            } else if (action.equals("creerUtilisateursDeTest")) {
                gestionnaireUtilisateurs.creerUtilisateursDeTest();
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }

                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers((page - 1) * elementsParPage,
                        elementsParPage);

                int elements = gestionnaireUtilisateurs.getElements();
                int numPage = (int) Math.ceil(elements * 1.0 / elementsParPage);

                request.setAttribute("listeDesUsers", liste);
                request.setAttribute("noOfPages", numPage);
                request.setAttribute("currentPage", page);

                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            } else if (action.equals("creerUnUtilisateur")) {
                gestionnaireUtilisateurs.creeUnUtilisateur(login, password, abo);
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }

                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers((page - 1) * elementsParPage,
                        elementsParPage);

                int elements = gestionnaireUtilisateurs.getElements();
                int numPage = (int) Math.ceil(elements * 1.0 / elementsParPage);

                request.setAttribute("listeDesUsers", liste);
                request.setAttribute("noOfPages", numPage);
                request.setAttribute("currentPage", page);

                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            } else if (action.equals("chercherParLogin")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.chercherParLogin(login);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLeUtilisateur";
                message = "Liste des utilisateurs par login";
            } else if (action.equals("modifierUnUtilisateur")) {
                gestionnaireUtilisateurs.modifieUnUtilisateur(nom, prenom, login, password);
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }

                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers((page - 1) * elementsParPage,
                        elementsParPage);

                int elements = gestionnaireUtilisateurs.getElements();
                int numPage = (int) Math.ceil(elements * 1.0 / elementsParPage);

                request.setAttribute("listeDesUsers", liste);
                request.setAttribute("noOfPages", numPage);
                request.setAttribute("currentPage", page);

                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            } else if (action.equals("supprimerUnUtilisateur")) {
                String[] check = request.getParameterValues("id");
                for (int i = 0; i < check.length; i++) {
                    gestionnaireUtilisateurs.supprimeUnUtilisateur(Integer.parseInt(check[i]));
                }
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }

                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers((page - 1) * elementsParPage,
                        elementsParPage);

                int elements = gestionnaireUtilisateurs.getElements();
                int numPage = (int) Math.ceil(elements * 1.0 / elementsParPage);

                request.setAttribute("listeDesUsers", liste);
                request.setAttribute("noOfPages", numPage);
                request.setAttribute("currentPage", page);

                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            } else {
                forwardTo = "index.jsp?action=todo";
                message = "La fonctionnalité pour le paramètre " + action + " est à implémenter !";
            }
        }

        RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message);

        dp.forward(request, response);
        // Après un forward, plus rien ne peut être exécuté après !  
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
