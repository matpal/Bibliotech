package org.psm.mp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

import org.psm.mp.models.Utente;
import org.psm.mp.services.DatabaseService;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class UtenteDAO {
    private final Connection conn;
    
    public UtenteDAO(ServletContext context){

        DatabaseService db = (DatabaseService)context.getAttribute("dbConnection");
        this.conn = db.getConnection();
    }
    
    public boolean inserisciUtente(Utente utente){
        try{
            PreparedStatement insert = conn.prepareStatement(
                    "INSERT INTO Utenti(nome,cognome,username,email,password,telefono, utenza)"+
                    "VALUE(?,?,?,?,?,?,?)"
            );
            insert.setString(1, utente.getNome());
            insert.setString(2, utente.getCognome());
            insert.setString(3, utente.getUsername());
            insert.setString(4, utente.getEmail());
            insert.setString(5, utente.getPassword());
            insert.setString(6, utente.getTelefono());
            insert.setString(7, utente.getTipologiaUtenza().toString());
            
            insert.executeUpdate();
            insert.close();
            return true;
            
        } catch(SQLException e){
            Logger.getLogger(UtenteDAO.class.getName()).log(Level.SEVERE,null, e);
            return false;
        }
        
       
    }
    
    public void aggiornaUtente(Utente utente) {
    
        try {
            
            PreparedStatement ps = conn.prepareStatement("UPDATE Utenti SET nome=?, cognome=?, username=?, email=?, password=?, telefono=?, utenza=? where id=?");
            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getUsername());
            ps.setString(4, utente.getEmail());
            ps.setString(5, utente.getPassword());
            ps.setString(6, utente.getTelefono());
            ps.setString(7, utente.getTipologiaUtenza().toString());
            
            ps.setInt(8, utente.getId());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void rimuoviUtente(int id){
        try{
            PreparedStatement rimuovi = conn.prepareStatement("DELETE FROM Utenti WHERE id=?");
            rimuovi.setInt(1, id);
            rimuovi.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(UtenteDAO.class.getName()).log(Level.SEVERE,null, e);
        }
    }
    
    public Utente getUser(int id){
        Utente utente = new Utente();
        
        try{
            PreparedStatement getUser = conn.prepareStatement("SELECT * FROM Utenti WHERE id=?");
            getUser.setInt(1, id);
            ResultSet rs = getUser.executeQuery();
            
            if(rs.next()){
                utente.setId(rs.getInt("id"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setTelefono(rs.getString("telefono"));
                utente.setTipologiaUtenza(rs.getString("utenza"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                
            }else{
                getUser.close();
                rs.close();
                
                return null;
            }
            
            getUser.close();
            rs.close();
            
        } catch(SQLException e){
            Logger.getLogger(UtenteDAO.class.getName()).log(Level.SEVERE,null, e);
        }
        
        
        return utente;
    }
    
    public Utente getUser(String username, String password){
        
        Utente utente = new Utente();
        
        try{
            
            String query = "SELECT * FROM Utenti WHERE username = ? AND password = ?";
           
            PreparedStatement getUser;
            getUser = conn.prepareStatement(query);
            getUser.setString(1, username);
            getUser.setString(2, password);
            
            ResultSet rs = getUser.executeQuery();
            
            if(rs.next()){
                utente.setId(rs.getInt("id"));
                utente.setCognome(rs.getString("cognome"));
                utente.setNome(rs.getString("nome"));
                utente.setEmail(rs.getString("email"));
                utente.setTelefono(rs.getString("telefono"));
                utente.setTipologiaUtenza(rs.getString("utenza"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                
            } else{
                getUser.close();
                rs.close();
            
                return null;
            }
            
            getUser.close();
            rs.close();
            
         } catch (SQLException e){
            Logger.getLogger(UtenteDAO.class.getName()).log(Level.SEVERE,null, e);
        }
        
        return utente;
    }
    
    public boolean authenticate(Utente utente, String username, String password ){
        boolean isAuthenticate = false;
        if(utente.getUsername().equalsIgnoreCase(username) && utente.getPassword().equalsIgnoreCase(password)){
            isAuthenticate=true;
        }
        
        return isAuthenticate;
        
    }
    
    public List<Utente> getAllUtenti() {
    
        List<Utente> utenti = new ArrayList<Utente>();
        
        try {
            
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Utenti");
        
            while(rs.next()) {
            
                Utente utente = new Utente();
                utente.setId(rs.getInt("id"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setUsername(rs.getString("username"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setTelefono(rs.getString("telefono"));
                utente.setTipologiaUtenza(rs.getString("utenza"));
                
                utenti.add(utente);
            
            }
            
        } catch (SQLException e) {
            Logger.getLogger(UtenteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return utenti;
    }
}
