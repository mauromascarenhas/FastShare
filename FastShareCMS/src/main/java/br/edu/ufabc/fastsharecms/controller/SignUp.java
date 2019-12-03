package br.edu.ufabc.fastsharecms.controller;

import br.edu.ufabc.fastsharecms.model.User;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
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
        
        String uName = request.getParameter("username"),
               fName = request.getParameter("full_name"),
               email = request.getParameter("email"),
               password = request.getParameter("password");

        System.out.println(uName);
        System.out.println(fName);
        System.out.println(email);
        System.out.println(password);

        User nuser = new User();
        nuser.setUsername(uName);
        nuser.setName(fName);
        nuser.setEmail(email);
        nuser.setRole("POSTER");
        nuser.setApproved(Boolean.FALSE);
        
        byte[] passByte = password.getBytes("UTF-8");
        byte[] toDigest = new byte[20 + passByte.length];
        byte[] salt = generateSalt();
        
        nuser.setPsalt(new String(salt));
        
        for (int i = 0; i < 20; ++i) toDigest[i] = salt[i];
        for (int i = 21; i < passByte.length + 20; ++i) toDigest[i] = passByte[i - 21];
        System.out.println(new String(salt));
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(toDigest);
            String pHash = String.format("%064x", new BigInteger(1, digest.digest()));
            System.out.println(pHash);
            nuser.setPhash(pHash);
            
            // TODO : Check if exists
            // TODO : Test user insertion
            
            redirectTo = "/users/create_success.html";
        } catch (NoSuchAlgorithmException e) {
            redirectTo = "/users/create_fail.html";
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
