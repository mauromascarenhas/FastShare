/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.fastsharecms.controller;

import br.edu.ufabc.fastsharecms.dao.UserDAO;
import br.edu.ufabc.fastsharecms.model.User;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        if (loggedIn) response.sendRedirect("/");
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
        String rid = request.getParameter("id");

        udao = UserDAO.getInstance();
        User suser = udao.select(new User(username));

        if (suser != null) {
            byte[] toDigest = (suser.getPsalt() + password).getBytes(Charset.forName("UTF-8"));

            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                digest.reset();
                digest.update(toDigest);
                
                if (String.format("%064x", new BigInteger(1, digest.digest())).equals(suser.getPhash()))
                    request.getSession().setAttribute("connected_user", suser);
                else {
                    request.getRequestDispatcher("/users/login_error.html").forward(request, response);
                    return;
                }
                
                if (redirect == null) {
                    response.sendRedirect("/");
                    return;
                }
                
                URIBuilder req = new URIBuilder(redirect);
                if (action == null) action = "create";
                if (rid == null) rid = "-1";
                req.addParameter("action", action);
                req.addParameter("id", rid);
                redirect = req.build().toString();
            } catch (NoSuchAlgorithmException e) {
                redirect = "/users/create_fail.html";
            } catch (URISyntaxException ex) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
            response.sendRedirect(redirect);
        }
        else request.getRequestDispatcher("/users/login_error.html").forward(request, response);
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
