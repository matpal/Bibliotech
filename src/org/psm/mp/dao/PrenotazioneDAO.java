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
import org.psm.mp.models.Prenotazione;
import org.psm.mp.services.DatabaseService;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class PrenotazioneDAO {
    private final Connection conn;
    private final ServletContext context;
    
    public PrenotazioneDAO(ServletContext context){
        
        this.context = context;
        DatabaseService db = (DatabaseService)context.getAttribute("dbConnection");
        this.conn = db.getConnection();
    }
    
    public boolean inserisciPrenotazione(Prenotazione prenotazione){
        try{
            LibroDAO dao = new LibroDAO(this.context);
            PreparedStatement insert = conn.prepareStatement(
                    "INSERT INTO Prenotazioni(idUtente,idLibro,titoloLibro,inizioPrenotazione,finePrenotazione)"+
                    "VALUE(?,?,?,?,?)"
            );
            int idLibro = prenotazione.getIdLibro();
            
            insert.setInt(1, prenotazione.getIdUtente());
            insert.setInt(2, idLibro);
            insert.setString(3, dao.getLibro(idLibro).getTitolo());
            
            java.sql.Date inizio = new java.sql.Date(prenotazione.getInizioPrenotazione().getTime());
            insert.setDate(4, inizio );
            java.sql.Date fine = new java.sql.Date(prenotazione.getFinePrenotazione().getTime());
            insert.setDate(5, fine);

            insert.executeUpdate();
            insert.close();
            return true;
            
        } catch(SQLException e){
            Logger.getLogger(PrenotazioneDAO.class.getName()).log(Level.SEVERE,null, e);
        }
        
        return false;
    }
    
    public Prenotazione getPrenotazione(int id){
        Prenotazione prenotazione = new Prenotazione();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Prenotazioni WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                prenotazione.setId(rs.getInt("id"));
                prenotazione.setIdLibro(rs.getInt("idLibro"));
                prenotazione.setTitoloLibro(rs.getString("titoloLibro"));
                prenotazione.setIdUtente(rs.getInt("idUtente"));
                prenotazione.setInizioPrenotazione(rs.getDate("inizioPrenotazione"));
                prenotazione.setFinePrenotazione(rs.getDate("finePrenotazione"));
                
            }else{
                ps.close();
                rs.close();
                
                return null;
            }
            
            ps.close();
            rs.close();
            
        } catch(SQLException e){
            Logger.getLogger(PrenotazioneDAO.class.getName()).log(Level.SEVERE,null, e);
        }
        
        
        return prenotazione;
    }
    
    public Prenotazione getPrenotazione(int idUtente, int idLibro){
        Prenotazione prenotazione = new Prenotazione();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Prenotazioni WHERE idUtente=? AND idLibro=?");
            ps.setInt(1, idUtente);
            ps.setInt(2, idLibro);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                prenotazione.setId(rs.getInt("id"));
                prenotazione.setIdLibro(rs.getInt("idLibro"));
                prenotazione.setTitoloLibro(rs.getString("titoloLibro"));
                prenotazione.setIdUtente(rs.getInt("idUtente"));
                prenotazione.setInizioPrenotazione(rs.getDate("inizioPrenotazione"));
                prenotazione.setFinePrenotazione(rs.getDate("finePrenotazione"));
                
                
            }else{
                ps.close();
                rs.close();
                
                return null;
            }
            
            ps.close();
            rs.close();
            
        } catch(SQLException e){
            Logger.getLogger(PrenotazioneDAO.class.getName()).log(Level.SEVERE,null, e);
        }
        
        
        return prenotazione;
    }
    
    
    public List<Prenotazione> getPrenotazioniByIdUtente(int id) {
    
        List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
        
        try {
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Prenotazioni WHERE idUtente=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
        
            while(rs.next()) {
            
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setId(rs.getInt("id"));
                prenotazione.setIdUtente(rs.getInt("idUtente"));
                prenotazione.setIdLibro(rs.getInt("idLibro"));
                prenotazione.setTitoloLibro(rs.getString("titoloLibro"));
                prenotazione.setInizioPrenotazione(rs.getDate("inizioPrenotazione"));
                prenotazione.setFinePrenotazione(rs.getDate("finePrenotazione"));
                prenotazioni.add(prenotazione);
            
            }
            
        } catch (SQLException e) {
            Logger.getLogger(PrenotazioneDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return prenotazioni;
    }
    
    public ArrayList<Prenotazione> getPrenotazioniByIdLibro(int id) {
    
        ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
        
        try {
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Prenotazioni WHERE idLibro=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
        
            while(rs.next()) {
            
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setId(rs.getInt("id"));
                prenotazione.setIdUtente(rs.getInt("idUtente"));
                prenotazione.setIdLibro(rs.getInt("idLibro"));
                prenotazione.setTitoloLibro(rs.getString("titoloLibro"));
                prenotazione.setInizioPrenotazione(rs.getDate("inizioPrenotazione"));
                prenotazione.setFinePrenotazione(rs.getDate("finePrenotazione"));
                
                prenotazioni.add(prenotazione);
            
            }
            
        } catch (SQLException e) {
            Logger.getLogger(PrenotazioneDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return prenotazioni;
    }
    
    public List<Prenotazione> getAllPrenotazioni() {
    
        List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
        
        try {
            
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Prenotazioni");
        
            while(rs.next()) {
            
                Prenotazione p = new Prenotazione();
                
                p.setId(rs.getInt("id"));
                p.setIdUtente(rs.getInt("idUtente"));
                p.setIdLibro(rs.getInt("idLibro"));
                p.setTitoloLibro(rs.getString("titoloLibro"));
                p.setInizioPrenotazione(rs.getDate("inizioPrenotazione"));
                p.setFinePrenotazione(rs.getDate("finePrenotazione"));
                
                
                prenotazioni.add(p);
            
            }
            
        } catch (SQLException e) {
            Logger.getLogger(PrenotazioneDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return prenotazioni;
    }
    
    public void rimuoviPrenotazione(int id){
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Prenotazioni WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
            
        } catch (SQLException e){
            Logger.getLogger(PrenotazioneDAO.class.getName()).log(Level.SEVERE,null, e);
        }
    }
    
}
