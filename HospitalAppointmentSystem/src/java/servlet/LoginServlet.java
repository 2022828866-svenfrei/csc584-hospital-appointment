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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author frei-
 */
public class LoginServlet extends HttpServlet {
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
        AccountBean account = new AccountBean();
        account.setEmail(request.getParameter("email"));        
        account.setPassword(request.getParameter("password"));
        
        if (account.getEmail() != null && !account.getEmail().isEmpty() &&
                account.getPassword() != null && !account.getEmail().isEmpty() &&
                AccountDao.isLoginSuccessfull(account)) {
            
            account = AccountDao.getAccountByEmail(account.getEmail());
            
            request.getSession().setAttribute("account"
                    , AccountDao.getAccountByEmail(account.getEmail()));
            
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else {
            request.setAttribute("message", "Email or password is incorrect!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }        
    }
}
