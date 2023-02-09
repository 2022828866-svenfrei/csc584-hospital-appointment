/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.AbsenceBean;
import bean.AppointmentBean;
import dao.AbsenceDao;
import dao.AccountDao;
import dao.AppointmentDao;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String appointmentId = request.getParameter("appointmentId");
        
        if (appointmentId != null && !appointmentId.isEmpty()) {
            request.setAttribute("appointment", AppointmentDao.getAppointmentById(Long.parseLong(appointmentId)));
        }
        
        setPageBeans(request);
        
        request.getRequestDispatcher("/CreateAppointment.jsp").forward(request, response);
    }
    
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
        AppointmentBean appointment = new AppointmentBean();
                
        String accountDoctorIdFK = request.getParameter("accountDoctorIdFK");
        String accountPatientIdFK = request.getParameter("accountPatientIdFK");
        String date = request.getParameter("date");
        String startTime = request.getParameter("startTime");
        String duration = request.getParameter("duration");
        String appointmentId = request.getParameter("appointmentId");

        String errorMessage = "";

        if (accountDoctorIdFK.isEmpty() || accountPatientIdFK.isEmpty() || date.isEmpty() || startTime.isEmpty() || duration.isEmpty()) 
        {
            errorMessage = "All fields are required";
        }
        else 
        {
            try {
                appointment.setAccountDoctorIdFK(Long.parseLong(accountDoctorIdFK));
                appointment.setAccountPatientIdFK(Long.parseLong(accountPatientIdFK));
                appointment.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
                appointment.setStartTime(new Time(timeFormat.parse(startTime).getTime()));
                appointment.setDuration(new Time(timeFormat.parse(duration).getTime()));
                appointment.setAppointmentId(Long.parseLong(appointmentId));
                
                if (appointment.getAppointmentId() > 0) {               
                    if (!AppointmentDao.updateAppointment(appointment)) {
                        errorMessage = "Error occurred while updating appointment!";
                    }
                } else {                    
                    if (!AppointmentDao.createAppointment(appointment)) {
                        errorMessage = "Error occurred while creating appointment!";
                    }
                }
            } catch (ParseException ex) {
                errorMessage = "Error occurred while converting inputs!";
            }
        }
        
        AbsenceBean absence = new AbsenceBean();
        
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        
        if (date == fromDate && date == toDate) {
            try {
                
                absence.setFromDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(fromDate).getTime()));
                absence.setToDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(toDate).getTime()));

                if (appointment.getDate() == absence.getFromDate() && appointment.getDate() == absence.getToDate()) {
                    errorMessage = "The Person is Absence that day!";
                }
                
            } catch (Exception ex) {
                    errorMessage = "The Person is Absence that day!";
            }
        }

        if (errorMessage.length() > 0) {
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("appointment", appointment);
            request.setAttribute("absence", absence);
            setPageBeans(request);
            request.getRequestDispatcher("/CreateAppointment.jsp").forward(request, response);
        }
        
        request.getRequestDispatcher("AppointmentDisplayServlet").forward(request, response);
    }
    
    private void setPageBeans(HttpServletRequest request) {        
        request.setAttribute("doctors", AccountDao.getAccounts(true));
        request.setAttribute("patients", AccountDao.getAccounts(false));
    }
}
