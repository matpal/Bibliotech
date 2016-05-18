package org.psm.mp.models;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class Prenotazione {
    private int id;
    private int idLibro;
    private String titoloLibro;
    private int idUtente;
    private Date inizioPrenotazione;
    private Date finePrenotazione;
    
    public Prenotazione(){
        // Inizializza il periodo di inizio della prenotazione
        this.setInizioPrenotazione(new Date());
        
        // Calcola 30 giorni dall'inizio della prenotazione
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getInizioPrenotazione());
        cal.add(Calendar.DAY_OF_MONTH, 30);
        
        // Imposta la data per la fine della prenotazione
        this.setFinePrenotazione(cal.getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public Date getInizioPrenotazione() {
        return inizioPrenotazione;
    }

    public void setInizioPrenotazione(Date inizioPrenotazione) {
        this.inizioPrenotazione = inizioPrenotazione;
    }

    public Date getFinePrenotazione() {
        return finePrenotazione;
    }

    public void setFinePrenotazione(Date finePrenotazione) {
        this.finePrenotazione = finePrenotazione;
    }

    public String getTitoloLibro() {
        return titoloLibro;
    }

    public void setTitoloLibro(String titoloLibro) {
        this.titoloLibro = titoloLibro;
    }
    
    public boolean isOver(){
        return this.finePrenotazione.before(new Date());
    }
    
}
