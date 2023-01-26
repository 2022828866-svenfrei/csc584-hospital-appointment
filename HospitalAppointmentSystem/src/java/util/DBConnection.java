/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author frei-
 */
public class DBConnection {
    public static Connection createConnection() {
        try {
            String driver = "org.apache.derby.jdbc.ClientDriver";
            String connectionString = "jdbc:derby://localhost:1527/HospitalAppointmentDB;create=true;user=app;password=app";
            
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
}
