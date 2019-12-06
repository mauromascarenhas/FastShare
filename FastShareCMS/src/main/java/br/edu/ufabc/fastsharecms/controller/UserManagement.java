/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.fastsharecms.controller;

import br.edu.ufabc.fastsharecms.dao.UserDAO;
import br.edu.ufabc.fastsharecms.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.utils.URIBuilder;

/**
 *
 * @author Mauro
 */
@WebServlet(name = "UserManagement", urlPatterns = {"/usermanagement", "/manageusers"})
public class UserManagement extends HttpServlet {

    private static final long serialVersionUID = 10L;

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
        User loggedUser = (User) request.getSession().getAttribute("connected_user");
        if (loggedUser == null) {
            String redirectURL = "/signin";
            try {
                URIBuilder redirect = new URIBuilder("/signin");
                redirect.addParameter("redirect", "/myposts");
                redirectURL = redirect.build().toString();
            } catch (URISyntaxException e) {
                redirectURL = "/404";
            } finally {
                response.sendRedirect(redirectURL);
            }
            return;
        }
        else if (!loggedUser.getRole().equals("ADMIN")){
            request.getRequestDispatcher("/users/forbidden.html").forward(request, response);
            return;
        }
        
        request.setAttribute("users", UserDAO.getInstance().selectAll());
        request.getRequestDispatcher("/usermanagement.jsp").forward(request, response);
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
        request.getRequestDispatcher("/404.html").forward(request, response);
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
