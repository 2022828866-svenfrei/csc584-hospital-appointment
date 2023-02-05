/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.AbsenceBean;
import bean.AccountBean;
import dao.AbsenceDao;
import dao.ExpertiseDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author frei-
 */
public class AbsenceUpdateServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountBean account = (AccountBean)request.getSession().getAttribute("account");
        
        String absenceId = request.getParameter("absenceId");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
                
        if (fromDate != null && !fromDate.isEmpty() &&
                toDate != null && !toDate.isEmpty()) {
            try {
                AbsenceBean absence = new AbsenceBean();
                absence.setAbsenceId(Long.parseLong(absenceId));
                absence.setAccountIdFK(account.getAccountId());
                absence.setFromDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(fromDate).getTime()));
                absence.setToDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(toDate).getTime()));

                if (!AbsenceDao.updateAbsence(absence)) {
                    request.setAttribute("absenceMessage", "Update was unsuccessful");
                }
            } catch (Exception ex) {
                    request.setAttribute("absenceMessage", "Update was unsuccessful");
            }
        }
        else {
            request.setAttribute("absenceMessage", "Please provide the necessary information.");
        }
        
        setPageBeans(request, account.getAccountId());
        request.getRequestDispatcher("/editAccount.jsp").forward(request, response); 
    }
    
    private void setPageBeans(HttpServletRequest request, long accountId) {
        request.setAttribute("expertises", ExpertiseDao.getExpertises());
        request.setAttribute("absences", AbsenceDao.getAbsencesByAccount(accountId));
    }
}
