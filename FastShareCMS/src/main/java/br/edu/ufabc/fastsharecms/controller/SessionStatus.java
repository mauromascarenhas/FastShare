package br.edu.ufabc.fastsharecms.controller;

import br.edu.ufabc.fastsharecms.model.User;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SessionStatus", urlPatterns = {"/sessionstatus"})
public class SessionStatus extends HttpServlet {

    private static final long serialVersionUID = 7L;
    
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
        response.setContentType("application/json");
        User conn = (User)request.getSession().getAttribute("connected_user");
        Boolean connected = conn != null;
        
        JsonObject obj = new JsonObject();
        obj.addProperty("connected", connected);
        if (conn != null){
            obj.addProperty("id", conn.getId());
            obj.addProperty("name", conn.getName());
            obj.addProperty("username", conn.getUsername());
            obj.addProperty("approved", conn.getApproved());
            obj.addProperty("email", conn.getEmail());
            obj.addProperty("role", conn.getRole());
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
