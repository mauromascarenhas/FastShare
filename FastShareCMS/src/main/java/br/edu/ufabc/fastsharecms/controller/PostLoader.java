/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.fastsharecms.controller;

import br.edu.ufabc.fastsharecms.dao.PostDAO;
import br.edu.ufabc.fastsharecms.model.Post;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mauro
 */
@WebServlet(name = "PostLoader", urlPatterns = {"/postloader"})
public class PostLoader extends HttpServlet {

    private static final long serialVersionUID = 2L;

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
        request.getRequestDispatcher("/404.html").forward(request, response);
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
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        String dateString = request.getParameter("date");
        Long pastLast = dateString == null ? new Date().getTime() : Long.parseLong(dateString);
        
        JsonArray obj = new JsonArray();
        List<Post> posts = PostDAO.getInstance().selectAllFromDate(pastLast);
        if (posts != null){
            for (Post p : posts){
                JsonObject postObj = new JsonObject();
                postObj.addProperty("id", p.getId());
                postObj.addProperty("author", p.getAuthor().getName());
                postObj.addProperty("title", p.getTitle());
                postObj.addProperty("description", p.getDescription());
                postObj.addProperty("linkto_url", p.getPostLink());
                postObj.addProperty("image_url", p.getImgURL());
                postObj.addProperty("date", p.getDate());
                obj.add(postObj);
            }
        }
        
        try (PrintWriter out = response.getWriter()) {
            out.print(obj.toString());
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
