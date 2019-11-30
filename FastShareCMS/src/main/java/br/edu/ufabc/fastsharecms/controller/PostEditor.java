/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.fastsharecms.controller;

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
@WebServlet(name = "PostEditor", urlPatterns = {"/editor"})
public class PostEditor extends HttpServlet {
    
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
        String redirectURL = "/editor.jsp";
        Object act = request.getAttribute("action");
        String action = act == null ? "create" : act.toString();
        
        User loggedUser = (User)request.getSession().getAttribute("conn_user");
        if (loggedUser == null){
            try{
                URIBuilder redirect = new URIBuilder("/signin");
                redirect.addParameter("redirect", "/editor")
                        .addParameter("action", action);
                redirectURL = redirect.build().toString();
            } catch (URISyntaxException e){
                redirectURL = "/404";
            } finally {
                response.sendRedirect(redirectURL);
            }
            return;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher(redirectURL).forward(request, response);
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
        
        String output = "LOGGED IN";
        
        User loggedUser = (User)request.getSession().getAttribute("conn_user");
        if (loggedUser == null) output = "NOT " + output;
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignIn</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignIn at " + output + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    }

}
