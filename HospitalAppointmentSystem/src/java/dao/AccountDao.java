/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.AccountBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class AccountDao {
    public static boolean isLoginSuccessfull(AccountBean accountBean) {
        Connection con = null;
        Statement statement = null;
        boolean isLoginSuccessful = false;
        
        try {            
            con = DBConnection.createConnection();
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select email, password from account where email='" + accountBean.getEmail() + "'");
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                
                if (email.equals(accountBean.getEmail())
                        && password.equals(accountBean.getPassword())) {
                    isLoginSuccessful = true;
                }
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
        
        return isLoginSuccessful;
    }
    
    public static boolean registerAccount(AccountBean accountBean) {
        Connection con = null;
        Statement statement = null;
        boolean isInsertSuccessful = true;
        
        try {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            int result = statement.executeUpdate("insert into Account (email, fullName, password, birthdate) "
                    + "VALUES ('" + accountBean.getEmail() + "', '"
                    + accountBean.getFullName() + "', '"
                    + accountBean.getPassword() + "', '"
                    + accountBean.getBirthDate() + "')");
            
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
    
    public static AccountBean getAccountByEmail(String email) {
        AccountBean account = null;
        Connection con = null;
        PreparedStatement statement = null;
        
        if (email != null && !email.isEmpty()) {
            try {
                con = DBConnection.createConnection();
                statement = con.prepareStatement("SELECT * FROM Account WHERE email=?");
                statement.setString(1, email);
                ResultSet rs = statement.executeQuery();
                
                if (rs.next()) {
                    account = new AccountBean(rs.getLong("accountId"),
                        rs.getLong("expertiseIdFK"),
                        rs.getString("email"),
                        rs.getString("fullName"),
                        rs.getString("password"),
                        rs.getDate("birthDate"),
                        rs.getBoolean("isDoctor"));
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
        }
        
        return account;
    }
    
    public static void updateAccount(AccountBean account) {
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            con = DBConnection.createConnection();
            statement = con.prepareStatement("UPDATE Account "
                    + "SET expertiseIdFK=?, email=?, fullName=?, birthdate=?, "
                    + "password=? "
                    + "WHERE accountId=?");
            statement.setLong(1, account.getExpertiseIdFK());
            statement.setString(2, account.getEmail());
            statement.setString(3, account.getFullName());
            statement.setDate(4, account.getBirthDate());
            statement.setString(5, account.getPassword());
            statement.setLong(6, account.getAccountId());
            statement.executeUpdate();
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
    
    public static List<AccountBean> getAccounts(boolean isDoctor) {
        List<AccountBean> doctors = new ArrayList<>();
        Connection con = null;
        Statement statement = null;
        
        try {            
            con = DBConnection.createConnection();
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Account WHERE isDoctor=" + (isDoctor ? "true" : "false")); 
            while (rs.next()) {
                doctors.add(new AccountBean(rs.getLong("accountId"),
                        rs.getLong("expertiseIdFK"),
                        rs.getString("email"),
                        rs.getString("fullName"),
                        rs.getString("password"),
                        rs.getDate("birthDate"),
                        rs.getBoolean("isDoctor")));
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
        
        return doctors;
    }
}
