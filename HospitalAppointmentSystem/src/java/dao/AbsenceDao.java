/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.AbsenceBean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import util.DBConnection;

/**
 *
 * @author frei-
 */
public class AbsenceDao {
    public static boolean insertAbsence(AbsenceBean absenceBean) {
        Connection con = null;
        PreparedStatement statement = null;
        boolean isInsertSuccessful = true;
        
        try {
            con = DBConnection.createConnection();
            statement = con.prepareStatement("INSERT INTO Absence (accountIdFK, fromDate, toDate) "
                    + "VALUES (?, ?, ?)");
            statement.setLong(1, absenceBean.getAccountIdFK());
            statement.setDate(2, absenceBean.getFromDate());
            statement.setDate(3, absenceBean.getToDate());
            
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
    
    public static boolean updateAbsence(AbsenceBean absenceBean) {
        Connection con = null;
        PreparedStatement statement = null;
        boolean isUpdateSuccessful = true;
        
        try {
            con = DBConnection.createConnection();
            statement = con.prepareStatement("UPDATE Absence "
                    + "SET fromDate=?, toDate=?"
                    + "WHERE absenceId=?");
            statement.setDate(1, absenceBean.getFromDate());
            statement.setDate(2, absenceBean.getToDate());
            statement.setLong(3, absenceBean.getAbsenceId());
            
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            isUpdateSuccessful = false;
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
        
        return isUpdateSuccessful;
    }
    
    public static List<AbsenceBean> getAbsencesByAccount(long accountId) {
        List<AbsenceBean> absences = new ArrayList<>();
        Connection con = null;
        Statement statement = null;
        
        try {            
            con = DBConnection.createConnection();
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Absence where accountIdFK=" + accountId);
            while (resultSet.next()) {
                absences.add(new AbsenceBean(resultSet.getLong("absenceId"),
                    resultSet.getLong("accountIdFK"),
                    resultSet.getDate("fromDate"),
                    resultSet.getDate("toDate")));
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
        
        return absences.stream()
                .filter(a -> a.getToDate().compareTo(new Date(new java.util.Date().getTime())) > 0)
                .collect(Collectors.toList());
    }
    
    public static void deleteAbsence(long absenceId) {
        Connection con = null;
        Statement statement = null;
        
        try {            
            con = DBConnection.createConnection();
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM Absence "
                    + "WHERE absenceId=" + absenceId);
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
    }
}
