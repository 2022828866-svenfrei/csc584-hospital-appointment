/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.AccountBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnection;

/**
 *
 * @author frei-
 */
public class AccountDao {    
    public static boolean isLoginSuccessfull(AccountBean accountBean) {
        try {            
            Connection con = DBConnection.createConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select email, password from account where email='" + accountBean.getEmail() + "'");
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                
                if (email.equals(accountBean.getEmail())
                        && password.equals(accountBean.getPassword())) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public static boolean registerAccount(AccountBean accountBean) {
        try {
            Connection con = DBConnection.createConnection();
            Statement statement = con.createStatement();
            int result = statement.executeUpdate("insert into Account (email, fullName, password, birthdate) "
                    + "VALUES ('" + accountBean.getEmail() + "', '"
                    + accountBean.getFullName() + "', '"
                    + accountBean.getPassword() + "', '"
                    + accountBean.getBirthDate() + "')");
            
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
}
