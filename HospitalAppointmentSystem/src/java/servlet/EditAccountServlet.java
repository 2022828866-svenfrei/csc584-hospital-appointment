/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.AccountBean;
import dao.AbsenceDao;
import dao.AccountDao;
import dao.ExpertiseDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author frei-
 */
public class EditAccountServlet extends HttpServlet {
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
        
        AccountBean account = (AccountBean)request.getSession().getAttribute("account");
        setPageBeans(request, account.getAccountId());
        
        request.getRequestDispatcher("/editAccount.jsp").forward(request, response);
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
        AccountBean account = (AccountBean)request.getSession().getAttribute("account");
        
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        if (password != null && !password.isEmpty() &&
                confirmPassword != null && !confirmPassword.isEmpty() &&
                password.equals(confirmPassword)) {
            try {
                AccountBean accountUpdate = new AccountBean();
                accountUpdate.setAccountId(account.getAccountId());
                accountUpdate.setEmail(request.getParameter("email"));
                accountUpdate.setPassword(password);
                accountUpdate.setFullName(request.getParameter("name"));
                accountUpdate.setBirthDate(
                        new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthdate")).getTime()));
                accountUpdate.setExpertiseIdFK(Long.parseLong(request.getParameter("expertise")));
                accountUpdate.setIsDoctor(account.isIsDoctor());
                
                if (accountUpdate.getEmail() != null && !accountUpdate.getEmail().isEmpty() &&
                        accountUpdate.getFullName() != null && !accountUpdate.getFullName().isEmpty()) {
                    
                    AccountDao.updateAccount(accountUpdate);
                    request.getSession().setAttribute("account", accountUpdate);
                }
                else {
                    request.setAttribute("message", "Update unsuccessful!");
                }
            }
            catch (Exception ex) {
                request.setAttribute("message", "Error during the update!");
            }
        }
        else {
            request.setAttribute("message", "The passwords are not the same!");
        }
        
        setPageBeans(request, account.getAccountId());
        request.getRequestDispatcher("/editAccount.jsp").forward(request, response);
    }
    
    private void setPageBeans(HttpServletRequest request, long accountId) {
        request.setAttribute("expertises", ExpertiseDao.getExpertises());
        request.setAttribute("absences", AbsenceDao.getAbsencesByAccount(accountId));
    }
}
