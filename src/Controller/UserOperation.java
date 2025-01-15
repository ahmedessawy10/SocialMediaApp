package Controller;

import Database.Db;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserOperation {
    Db db = new Db() ;
    
   
    
     public boolean registration(String tableName , String userName , String email  ,String password) throws SQLException{
//        
//          ResultSet result = db.executequery(String.format("SELECT * FROM %s where Email = '%s' ", tableName , email )) ; 
//          if (result.next() == false){
//          int affectedRow = db.executeupdate(String.format("INSERT INTO %s (UserName , Email , Password) VALUES('%s' , '%s' , '%s')",tableName,userName , email,password));    
//          db.close() ; 
//          return true ; 
//          }
//          db.close() ; 
//          return false ;
 // Correctly format the email within single quotes
    ResultSet result = db.executequery(String.format("SELECT * FROM %s WHERE Email = '%s'", tableName, email)); 

    if (result.next() == false) {
        // If no user exists with the given email, insert a new user
        int affectedRow = db.executeupdate(String.format("INSERT INTO %s (UserName, Email, Password) VALUES('%s', '%s', '%s')", tableName, userName, email, password));
        db.close();
        return true;
    }

    db.close();
    return false;
    }
    public boolean registration(String tableName , String userName , String email  ,String password , String street) throws SQLException{
        ResultSet result = db.executequery(String.format("SELECT * FROM %s where Email = '%s' ", tableName , email )) ; 
          if (result.next() == false){
          int affectedRow = db.executeupdate(String.format("INSERT INTO %s (UserName , Email , Password , Street) VALUES('%s' , '%s' , '%s' , '%s')",tableName,userName , email,password , street));    
          db.close();
          return true ; 
          }
          db.close() ; 
          return false ;
    }
    public boolean  registration( String tableName, String userName , String email  ,String password , String street , String city) throws SQLException{
        ResultSet result = db.executequery(String.format("SELECT * FROM %s where Email = '%s' ", tableName , email )) ; 
          if (result.next() == false){
          int affectedRow = db.executeupdate(String.format("INSERT INTO %s (UserName , Email , Password , Street) VALUES('%s' , '%s' , '%s' , '%s' , '%s')",tableName,userName , email,password , street , city));    
          db.close();
          return true ; 
          }
          db.close() ; 
          return false ;
        
    }
    public boolean registration(String tableName , String userName , String email  ,String password , String street , String city , String country) throws SQLException{
        ResultSet result = db.executequery(String.format("SELECT * FROM %s where Email = '%s' ", tableName , email )) ; 
          if (result.next() == false){
          int affectedRow = db.executeupdate(String.format("INSERT INTO %s (UserName , Email , Password , Street) VALUES('%s' , '%s' , '%s' , '%s' , '%s' , '%s')",tableName,userName , email,password , street , city , country));    
          db.close() ; 
          return true ; 
          }
          db.close() ; 
          return false ;
    }
    public void login(String email , String password){
        
        
    }
    
}
