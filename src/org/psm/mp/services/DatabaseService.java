package org.psm.mp.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class DatabaseService {
    
    private Connection conn = null;
    
    public DatabaseService(String driver, String url, String user, String password){
        try{
            
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e){
            Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public Connection getConnection(){
        return this.conn;
    }
    
}
