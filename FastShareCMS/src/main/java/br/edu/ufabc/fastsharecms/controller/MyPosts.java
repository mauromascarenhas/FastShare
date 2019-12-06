package br.edu.ufabc.fastsharecms.controller;

import br.edu.ufabc.fastsharecms.dao.PostDAO;
import br.edu.ufabc.fastsharecms.model.Post;
import br.edu.ufabc.fastsharecms.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "MyPosts", urlPatterns = {"/myposts", "/my-posts"})
public class MyPosts extends HttpServlet {

    private static final long serialVersionUID = 9L;

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
        User loggedUser = (User)request.getSession().getAttribute("connected_user");
        if (loggedUser == null){
            String redirectURL = "/signin";
            try{
                URIBuilder redirect = new URIBuilder("/signin");
                redirect.addParameter("redirect", "/myposts");
                redirectURL = redirect.build().toString();
            } catch (URISyntaxException e){
                redirectURL = "/404";
            } finally {
                response.sendRedirect(redirectURL);
            }
            return;
        }
        
        List<Post> results = PostDAO.getInstance().selectAllAuthor(loggedUser.getId());
        if (results == null) results = new ArrayList<>();
        request.setAttribute("results", results);
        request.getRequestDispatcher("/myposts.jsp").forward(request, response);
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
    }

}
