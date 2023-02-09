/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.AppointmentBean;
import dao.AppointmentDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HYUGA
 */
@WebServlet(name = "AppointmentServlet", urlPatterns = {"/AppointmentServlet"})
public class AppointmentServlet extends HttpServlet 
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String accountDoctorIdFK = request.getParameter("accountDoctorIdFK");
        String accountPatientIdFK = request.getParameter("accountPatientIdFK");
        String date = request.getParameter("date");
        String startTime = request.getParameter("startTime");
        String duration = request.getParameter("duration");

        String errorMessage = "";
        String successMessage = "";

        if (accountDoctorIdFK.isEmpty() || accountPatientIdFK.isEmpty() || date.isEmpty() || startTime.isEmpty() || duration.isEmpty()) 
        {
            errorMessage = "All fields are required";
        }
        else 
        {
            AppointmentBean appointment = new AppointmentBean();
            appointment.setAccountDoctorIdFK(accountDoctorIdFK);
            appointment.setAccountPatientIdFK(accountPatientIdFK);
            appointment.setDate(date);
            appointment.setStartTime(startTime);
            appointment.setDuration(startTime);

            AppointmentDao appointmentDao = new AppointmentDao();
            String createApp = appointmentDao.createAppointment(appointmentBean);

            if (createApp.equals("SUCCESS")) {
                successMessage = "Appointment created successfully!";
            } else {
                errorMessage = "Error occurred while creating appointment: " + createApp;
            }
        }

        if (errorMessage.length() > 0) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/CreateAppointment.jsp").forward(request, response);
        } else {
            request.setAttribute("successMessage", successMessage);
            request.getRequestDispatcher("/appointmentView.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/appointmentView.jsp").forward(request, response);
        
    }    	
}
