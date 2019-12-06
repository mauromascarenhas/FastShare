package br.edu.ufabc.fastsharecms.controller;

import br.edu.ufabc.fastsharecms.dao.PostDAO;
import br.edu.ufabc.fastsharecms.model.Post;
import br.edu.ufabc.fastsharecms.model.User;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
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

    private static final long serialVersionUID = 6L;
    
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
        String sid = request.getParameter("id");
        String act = request.getParameter("action");
        if (sid == null || sid.isEmpty()) sid = "-1";
        if (act == null || act.isEmpty()) act = "create";
        
        User loggedUser = (User)request.getSession().getAttribute("connected_user");
        if (loggedUser == null){
            try{
                URIBuilder redirect = new URIBuilder("/signin");
                redirect.addParameter("redirect", "/editor")
                        .addParameter("action", act)
                        .addParameter("id", sid);
                redirectURL = redirect.build().toString();
            } catch (URISyntaxException e){
                redirectURL = "/404";
            } finally {
                response.sendRedirect(redirectURL);
            }
            return;
        }
        else if (!loggedUser.getApproved()) redirectURL = "/users/no_access.html";
        
        if (act.equals("edit")){
            if (sid.equals("-1")) redirectURL = "/404.html";
            else {
                Post p = PostDAO.getInstance().select(Long.parseLong(sid));
                if (p != null){
                    if (!p.getAuthor().getId().equals(loggedUser.getId())) redirectURL = "/users/forbidden.html";
                    else request.setAttribute("content", p);
                }
                else redirectURL = "/404.html";
            }
        }
        else {
            Post p = new Post();
            p.setId(-1L);
            p.setAuthor(loggedUser);
            request.setAttribute("content", p);
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
        Object act = request.getAttribute("action");
        String action = act == null ? "create" : act.toString();
        
        String redirectURL = "/editor.jsp";
        response.setContentType("text/html;charset=UTF-8");
        User loggedUser = (User)request.getSession().getAttribute("connected_user");
        if (loggedUser == null){
            try{
                URIBuilder redirect = new URIBuilder("/signin");
                redirect.addParameter("redirect", "/editor")
                        .addParameter("action", action);
                redirectURL = redirect.build().toString();
            } catch (URISyntaxException e){
                redirectURL = "/500";
            } finally {
                response.sendRedirect(redirectURL);
            }
            return;
        }
        
        Long cid = Long.parseLong(request.getParameter("id"));
        if (cid.equals(-1L)){
            Post post = new Post();
            post.setTitle(request.getParameter("title"));
            post.setImgURL(request.getParameter("image-url"));
            post.setDescription(request.getParameter("description"));
            post.setAuthor(loggedUser);
            post.setDate(new Date().getTime());
            post.setFlags(0L);
            post.setPostLink(request.getParameter("link"));
            
            if (PostDAO.getInstance().insert(post)){
                response.setContentType("text/html;charset=UTF-8");
                request.getRequestDispatcher("/posts/success.html").forward(request, response);
                return;
            }
        }
        else {
            Post post = PostDAO.getInstance().select(cid);
            if (post == null){
                response.setContentType("text/html;charset=UTF-8");
                request.getRequestDispatcher("/posts/fail.html").forward(request, response);
                return;
            }
            
            post.setTitle(request.getParameter("title"));
            post.setImgURL(request.getParameter("image-url"));
            post.setDescription(request.getParameter("description"));
            post.setPostLink(request.getParameter("link"));
            
            if (PostDAO.getInstance().update(cid, post)){
                response.setContentType("text/html;charset=UTF-8");
                request.getRequestDispatcher("/posts/success.html").forward(request, response);
                return;
            }
        }
        
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/posts/fail.html").forward(request, response);
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
