/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.AppointmentBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        try 
        {            
            Connection con = DBConnection.createConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select date from");
            while (resultSet.next()) 
            {
                String date = resultSet.getString("date");
                String startTime = resultSet.getString("startTime");
                
                if (date.equals(appointmentBean.getDate())
                        && startTime.equals(appointmentBean.getStartTime())) {
                    return true;
                }
            }
        } catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        
        return false;
    }

    public String createAppointment(AppointmentDao appointmentDao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
