/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author SHEHAB
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Db {
    public Connection connectDb() {
        Connection connect = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection
            connect = DriverManager.getConnection("jdbc:mysql://localhost/socail media project", "root", "");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }

        
        
        
        
        
        
        return connect;
    }
    // that use in select query and return ResultSet 
    public ResultSet executequery(String query){
        try {
            Statement stmt  = connectDb().createStatement() ; 
            return stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ; 
    }
    // that use in insert update and delete and it return the affted row 
    public int  executeupdate(String query) throws SQLException {
        Statement stmt = connectDb().createStatement() ; 
        return stmt.executeUpdate(query) ; 
        
    }
    public void close() throws SQLException {
        if (connectDb() != null) {
            connectDb().close();
        }
    }
    
    
    
     
}