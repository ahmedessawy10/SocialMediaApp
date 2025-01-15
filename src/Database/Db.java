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
import java.sql.SQLException;

public class Db {
    public Connection connectDb() {
        Connection connect = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection
            connect = DriverManager.getConnection("jdbc:mysql://localhost/socialnetwork", "root", "");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }

        return connect;
    }
}