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
import org.psm.mp.models.Libro;

import org.psm.mp.services.DatabaseService;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class LibroDAO {
    private final Connection conn;
    
    public LibroDAO(ServletContext context){

        DatabaseService db = (DatabaseService)context.getAttribute("dbConnection");
        this.conn = db.getConnection();

    }
    
    public boolean inserisciLibro(Libro libro){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO Libri(isbn,titolo,autore,editore,genere,quantita,contatorePrenotazioni)"+
                    "VALUE(?,?,?,?,?,?,?)"
            );
            ps.setString(1, libro.getIsbn());
            ps.setString(2, libro.getTitolo());
            ps.setString(4, libro.getAutore());
            ps.setString(3, libro.getEditore());
            ps.setString(5, libro.getGenere());
            ps.setInt(6, libro.getQuantita());
            ps.setInt(7, libro.getContatorePrenotazioni());
            
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch(SQLException e){
            Logger.getLogger(UtenteDAO.class.getName()).log(Level.SEVERE,null, e);
            return false;
        }
        
        
    }
    
    public void aggiornaLibro(Libro libro) {
    
        try {
            
            PreparedStatement ps = conn.prepareStatement("UPDATE Libri SET isbn=?, titolo=?, autore=?, editore=?, genere=?, quantita=?, contatorePrenotazioni=? where id=?");
            ps.setString(1, libro.getIsbn());
            ps.setString(2, libro.getTitolo());
            ps.setString(3, libro.getAutore());
            ps.setString(4, libro.getEditore());
            ps.setString(5, libro.getGenere());
            ps.setInt(6, libro.getQuantita());
            ps.setInt(7, libro.getContatorePrenotazioni());
            ps.setInt(8, libro.getId());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void rimuoviLibro(int id){
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Libri WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
            
        } catch (SQLException e){
            Logger.getLogger(UtenteDAO.class.getName()).log(Level.SEVERE,null, e);
        }
    }
    
    public Libro getLibro(int id){
        Libro libro = new Libro();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Libri WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                libro.setId(rs.getInt("id"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setTitolo(rs.getString("titolo"));
                libro.setAutore(rs.getString("autore"));
                libro.setEditore(rs.getString("editore"));
                libro.setGenere(rs.getString("genere"));
                libro.setQuantita(rs.getInt("quantita"));
                libro.setContatorePrenotazioni(rs.getInt("contatorePrenotazioni"));
                
            }else{
                ps.close();
                rs.close();
                
                return null;
            }
            
            ps.close();
            rs.close();
            
        } catch(SQLException e){
            Logger.getLogger(UtenteDAO.class.getName()).log(Level.SEVERE,null, e);
        }
        
        
        return libro;
    }
    
    public List<Libro> getAllLibri() {
    
        List<Libro> libri = new ArrayList<Libro>();
        
        try {
            
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Libri");
        
            while(rs.next()) {
            
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setTitolo(rs.getString("titolo"));
                libro.setAutore(rs.getString("autore"));
                libro.setEditore(rs.getString("editore"));
                libro.setGenere(rs.getString("genere"));
                libro.setQuantita(rs.getInt("quantita"));
                libro.setContatorePrenotazioni(rs.getInt("contatorePrenotazioni"));
                
                libri.add(libro);
            
            }
            
        } catch (SQLException e) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return libri;
    }
    
    public List<Libro> getLatestLibri() {
    
        List<Libro> libri = new ArrayList<Libro>();
        
        try {
            
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Libri ORDER BY id DESC LIMIT 5");
        
            while(rs.next()) {
            
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setTitolo(rs.getString("titolo"));
                libro.setAutore(rs.getString("autore"));
                libro.setEditore(rs.getString("editore"));
                libro.setGenere(rs.getString("genere"));
                libro.setQuantita(rs.getInt("quantita"));
                libro.setContatorePrenotazioni(rs.getInt("contatorePrenotazioni"));
                
                libri.add(libro);
            
            }
            
        } catch (SQLException e) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return libri;
    }
    
    public List<Libro> getLibriBy(String column, String value) {
    
        List<Libro> libri = new ArrayList<Libro>();
        PreparedStatement ps;
        
        try{
            if(column.equalsIgnoreCase("titolo")){
                ps = conn.prepareStatement("SELECT * FROM Libri WHERE titolo LIKE ?");
                ps.setString(1,"%"+value+"%");
            }else if(column.equalsIgnoreCase("autore")){
                ps = conn.prepareStatement("SELECT * FROM Libri WHERE autore LIKE ?");
                ps.setString(1, "%"+value+"%");
            }else if(column.equalsIgnoreCase("genere")){
                ps = conn.prepareStatement("SELECT * FROM Libri WHERE genere LIKE ?");
                ps.setString(1, "%"+value+"%");
            }else{
                return null;
            }
            ResultSet rs = ps.executeQuery();
        
            while(rs.next()) {
            
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setTitolo(rs.getString("titolo"));
                libro.setAutore(rs.getString("autore"));
                libro.setEditore(rs.getString("editore"));
                libro.setGenere(rs.getString("genere"));
                libro.setQuantita(rs.getInt("quantita"));
                libro.setContatorePrenotazioni(rs.getInt("contatorePrenotazioni"));
                libri.add(libro);
            }
            rs.close();
            ps.close();
        }catch (SQLException e) {
                Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
        return libri;
    }
    
    public List<Libro> getMostRentedLibri() {
    
        List<Libro> libri = new ArrayList<Libro>();
        
        try {
            
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Libri ORDER BY contatorePrenotazioni DESC LIMIT 5");
        
            while(rs.next()) {
            
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setTitolo(rs.getString("titolo"));
                libro.setAutore(rs.getString("autore"));
                libro.setEditore(rs.getString("editore"));
                libro.setGenere(rs.getString("genere"));
                libro.setQuantita(rs.getInt("quantita"));
                libro.setContatorePrenotazioni(rs.getInt("contatorePrenotazioni"));
                
                libri.add(libro);
            
            }
            
        } catch (SQLException e) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return libri;
    }
    
}
