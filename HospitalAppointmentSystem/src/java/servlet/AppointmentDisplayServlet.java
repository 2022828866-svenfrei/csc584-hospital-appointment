/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.AccountBean;
import dao.AccountDao;
import dao.AppointmentDao;
import dao.ExpertiseDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author frei-
 */
public class AppointmentDisplayServlet extends HttpServlet {

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountBean account = (AccountBean)request.getSession().getAttribute("account");
        request.setAttribute("appointments", AppointmentDao.getAppointmentsByAccount(account.getAccountId()).stream()
                .filter(a -> a.getDate().compareTo(new Date(new java.util.Date().getTime())) > 0)
                .collect(Collectors.toList()));
        request.setAttribute("doctors", AccountDao.getAccounts(true));
        request.setAttribute("patients", AccountDao.getAccounts(false));
        
        request.getRequestDispatcher("/appointmentView.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        handleRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        handleRequest(request, response);
    }
}
