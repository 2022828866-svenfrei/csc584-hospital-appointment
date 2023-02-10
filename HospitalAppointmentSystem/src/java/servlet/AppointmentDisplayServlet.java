/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.AccountBean;
import bean.AppointmentBean;
import dao.AccountDao;
import dao.AppointmentDao;
import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
        
        List<AppointmentBean> appointments = AppointmentDao.getAppointmentsByAccount(account.getAccountId());
        Collections.sort(appointments,
                new Comparator<AppointmentBean>() {
                    public int compare(AppointmentBean o1, AppointmentBean o2) {
                       Date a = o1.getDate();
                       Date b = o2.getDate();
                       return b.compareTo(a);
                    }
                });
        
        request.setAttribute("appointments", appointments);
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
