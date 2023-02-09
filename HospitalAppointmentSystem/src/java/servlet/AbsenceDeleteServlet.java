/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.AccountBean;
import dao.AbsenceDao;
import dao.ExpertiseDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author frei-
 */
public class AbsenceDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountBean account = (AccountBean)request.getSession().getAttribute("account");
        
        String absenceId = request.getParameter("absenceId");
        
        AbsenceDao.deleteAbsence(Long.parseLong(absenceId));
        
        setPageBeans(request, account.getAccountId());
        request.getRequestDispatcher("/editAccount.jsp").forward(request, response); 
    }
    
    private void setPageBeans(HttpServletRequest request, long accountId) {
        request.setAttribute("expertises", ExpertiseDao.getExpertises());
        request.setAttribute("absences", AbsenceDao.getAbsencesByAccount(accountId));
    }

}
