package org.psm.mp.listeners;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.psm.mp.services.DatabaseService;

/**
 * Web application lifecycle listener.
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class BiblioTechListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
 
    	String url = sc.getInitParameter("dbUrl");
    	String user = sc.getInitParameter("dbUtente");
    	String password = sc.getInitParameter("dbPassword");
    	String driver = sc.getInitParameter("dbDriver");
    	DatabaseService db = new DatabaseService(driver,url,user,password);
    	sc.setAttribute("dbConnection", db);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       ServletContext sc = sce.getServletContext();
       DatabaseService db = (DatabaseService) sc.getAttribute("dbConnection");

        try {
            db.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BiblioTechListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
