package br.edu.ufabc.fastsharecms.controller;

import java.io.IOException;
import java.util.Random;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mauro
 */
@WebServlet(name = "SignUp", urlPatterns = {"/signup"})
public class SignUp extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        System.out.println(Arrays.toString(generateSalt()));
        response.setContentType("text/html;charset=UTF-8");
        getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
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
        String redirectTo = "/404.html";
        
        try {
            String uName = request.getParameter("username"),
                   fName = request.getParameter("full_name"),
                   email = request.getParameter("email"),
                   password = request.getParameter("password");
            
            System.out.println(uName);
            System.out.println(fName);
            System.out.println(email);
            System.out.println(password);
            
            
            redirectTo = "/404.html";
        } catch (Exception e) {
            redirectTo = "/500.html";
        } finally {
            getServletContext().getRequestDispatcher(redirectTo).forward(request, response);
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
    
    private byte [] generateSalt(){
        byte[] salt = new byte[20];
        new Random().nextBytes(salt);
        return salt;
    }
}
