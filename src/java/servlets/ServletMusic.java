/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import gestionnaires.GestionnaireMusiques;
import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modeles.musique.Morceau;
/**
 *
 * @author MoMo
 */
@WebServlet(name = "ServletMusic", urlPatterns = {"/ServletMusic"})
public class ServletMusic extends HttpServlet {

    @EJB
    private GestionnaireMusiques gestionnaireMusiques;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pratique pour décider de l'action à faire  
        String action = request.getParameter("action");
        String forwardTo = "";
        String message = "";

        int page = 1;
        int elementsParPage = 10;

        if (action != null) {
            if (action.equals("listerMusic")) {
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }

                Collection<Morceau> liste = gestionnaireMusiques.getAllMusic((page - 1) * elementsParPage,
                        elementsParPage);

                int elements = gestionnaireMusiques.getElements();
                int numPage = (int) Math.ceil(elements * 1.0 / elementsParPage);

                request.setAttribute("listMusic", liste);
                request.setAttribute("noOfPages", numPage);
                request.setAttribute("currentPage", page);

                forwardTo = "index.jsp?action=listerMusic";
                message = "Liste des musiques";
              
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
