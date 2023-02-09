/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.AccountBean;
import bean.AppointmentBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import util.DBConnection;

/**
 *
 * @author HYUGA
 */
@WebServlet(name = "AppointmentDao", urlPatterns = {"/AppointmentDao"})
public class AppointmentDao
{
    public static boolean createAppointment(AppointmentBean appointmentBean) {
        Connection con = null;
        PreparedStatement statement = null;
        boolean isInsertSuccessful = true;
        
        try {
            con = DBConnection.createConnection();
            statement = con.prepareStatement("Insert into Appointment (accountDoctorIdFk, accountPatientIdFk, date, starttime, duration) Values(?,?,?,?,?)");
            statement.setLong(1, appointmentBean.getAccountDoctorIdFK());
            statement.setLong(2, appointmentBean.getAccountPatientIdFK());
            statement.setDate(3, appointmentBean.getDate());
            statement.setTime(4, appointmentBean.getStartTime());
            statement.setTime(5, appointmentBean.getDuration());
            
            statement.executeUpdate();            
        } catch (SQLException ex) {
            ex.printStackTrace();
            isInsertSuccessful = false;
        } finally {
            try {
                if (con != null)
                    con.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return isInsertSuccessful;
    }

    public static boolean updateAppointment(AppointmentBean appointmentBean) {
        Connection con = null;
        PreparedStatement statement = null;
        boolean isInsertSuccessful = true;
        
        try {
            con = DBConnection.createConnection();
            statement = con.prepareStatement("Update Appointment Set "
                    + "accountDoctorIdFk=?, accountPatientIdFk=?, date=?, starttime=?, duration=? "
                    + "Where appointmentId=?");
            statement.setLong(1, appointmentBean.getAccountDoctorIdFK());
            statement.setLong(2, appointmentBean.getAccountPatientIdFK());
            statement.setDate(3, appointmentBean.getDate());
            statement.setTime(4, appointmentBean.getStartTime());
            statement.setTime(5, appointmentBean.getDuration());
            statement.setLong(6, appointmentBean.getAppointmentId());
            
            statement.executeUpdate();            
        } catch (SQLException ex) {
            ex.printStackTrace();
            isInsertSuccessful = false;
        } finally {
            try {
                if (con != null)
                    con.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return isInsertSuccessful;
    }
    
    public static AppointmentBean getAppointmentById(long appointmentId) {
        AppointmentBean appointment = null;
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            con = DBConnection.createConnection();
            statement = con.prepareStatement("SELECT * FROM Appointment WHERE appointmentId=?");
            statement.setLong(1, appointmentId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                appointment = new AppointmentBean(appointmentId,
                    rs.getLong("accountDoctorIdFK"),
                    rs.getLong("accountPatientIdFK"),
                    rs.getDate("date"),
                    rs.getTime("startTime"),
                    rs.getTime("duration"));
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return appointment;
    }
    
    public static List<AppointmentBean> getAppointmentsByAccount(long accountId) {
        List<AppointmentBean> appointments = new ArrayList<>();
        Connection con = null;
        Statement statement = null;
        
        try {            
            con = DBConnection.createConnection();
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Appointment WHERE accountDoctorIdFK="+accountId+" OR accountPatientIdFK="+accountId); 
            while (rs.next()) {
                appointments.add(new AppointmentBean(rs.getLong("appointmentId"),
                    rs.getLong("accountDoctorIdFK"),
                    rs.getLong("accountPatientIdFK"),
                    rs.getDate("date"),
                    rs.getTime("startTime"),
                    rs.getTime("duration")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return appointments;
    }
    
    public static boolean deleteAppointmentById(long appointmentId) {
        Connection con = null;
        Statement statement = null;
        boolean isDeleteSuccessful = true;
        
        try {            
            con = DBConnection.createConnection();
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM Appointment "
                    + "WHERE appointmentId=" + appointmentId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            isDeleteSuccessful=false;
        } finally {
            try {
                if (con != null)
                    con.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return isDeleteSuccessful;
    }
}
