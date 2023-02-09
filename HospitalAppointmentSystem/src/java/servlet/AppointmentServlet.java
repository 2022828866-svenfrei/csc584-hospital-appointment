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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @Override
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
            try {
                AppointmentBean appointment = new AppointmentBean();
                appointment.setAccountDoctorIdFK(Long.parseLong(accountDoctorIdFK));
                appointment.setAccountPatientIdFK(Long.parseLong(accountPatientIdFK));
                appointment.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
                appointment.setStartTime(new Time(timeFormat.parse(startTime).getTime()));
                appointment.setDuration(new Time(timeFormat.parse(duration).getTime()));

                if (AppointmentDao.createAppointment(appointment)) {
                    successMessage = "Appointment created successfully!";
                } else {
                    errorMessage = "Error occurred while creating appointment!";
                }
            } catch (ParseException ex) {
                errorMessage = "Error occurred while creating appointment!";
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
