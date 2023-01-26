/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.AccountBean;
import dao.AccountDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author frei-
 */
public class RegistrationServlet extends HttpServlet {

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
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        if (password != null && !password.isEmpty() &&
                confirmPassword != null && !confirmPassword.isEmpty() &&
                password.equals(confirmPassword)) {
            try {
                AccountBean account = new AccountBean();
                account.setEmail(request.getParameter("email"));
                account.setPassword(password);
                account.setFullName(request.getParameter("name"));
                account.setBirthDate(
                        new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthdate")).getTime()));
                
                if (account.getEmail() != null && !account.getEmail().isEmpty() &&
                        account.getFullName() != null && !account.getFullName().isEmpty() &&
                        AccountDao.registerAccount(account)) {
                    
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    return;
                }
                else {
                    request.setAttribute("message", "Registration unsuccessful!");
                }
            }
            catch (Exception ex) {
                request.setAttribute("message", "Error during the registration!");
            }
        }
        else {
            request.setAttribute("message", "The passwords are not the same!");
        }
        
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for registration processing";
    }
}
