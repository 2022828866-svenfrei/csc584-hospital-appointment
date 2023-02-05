/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.AccountBean;
import bean.ExpertiseBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author frei-
 */
public class ExpertiseDao {
    public static List<ExpertiseBean> getExpertises() {
        List<ExpertiseBean> expertises = new ArrayList<>();
        Connection con = null;
        Statement statement = null;
        
        try {            
            con = DBConnection.createConnection();
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM expertise"); 
            while (resultSet.next()) {
                expertises.add(new ExpertiseBean(resultSet.getLong("expertiseId"),
                    resultSet.getString("name"),
                    resultSet.getString("description")));
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
        
        return expertises;
    }
}
