/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.fastsharecms.controller;

import br.edu.ufabc.fastsharecms.dao.UserDAO;
import br.edu.ufabc.fastsharecms.model.User;
import java.io.IOException;
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
@WebServlet(name = "SignIn", urlPatterns = {"/signin", "/login"})
public class SignIn extends HttpServlet {

    private UserDAO udao;
    private static final long serialVersionUID = 5L;

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
        User loggedUser = (User)request.getSession().getAttribute("conn_user");
        Boolean loggedIn = loggedUser != null;
        
        response.setContentType("text/html;charset=UTF-8");
        if (loggedIn) response.sendRedirect("/editor.jsp");
        else request.getRequestDispatcher("/signin.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String redirect = request.getParameter("redirect");
        String action = request.getParameter("action");

        udao = UserDAO.getInstance();
        User suser = udao.select(serialVersionUID);

        if (suser != null) {
            suser.setUsername(username);
            request.getSession().setAttribute("connected_user", suser);
        }
        
        String forward = "/";
        if (redirect == null) response.sendRedirect(forward);
        try {
            URIBuilder req = new URIBuilder(redirect);
            if (action == null) action = "create";
            req.addParameter("action", action);
            forward = req.build().toString();
        } catch (URISyntaxException ex) {
            response.sendRedirect("/500");
        } finally {
            response.sendRedirect(forward);
        }
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
