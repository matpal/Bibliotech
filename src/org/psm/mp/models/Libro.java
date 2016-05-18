package org.psm.mp.models;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class Libro {
    private int id;
    private String isbn;
    private String titolo;
    private String autore;
    private String editore;
    private String genere;
    private int quantita;
    private int contatorePrenotazioni;
    private boolean disponibile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getEditore() {
        return editore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        if(quantita >= 0)
            this.quantita = quantita;
        else
            this.quantita = 0;
    }

    public boolean isDisponibile() {
        return this.quantita > 0;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    public int getContatorePrenotazioni() {
        return contatorePrenotazioni;
    }

    public void setContatorePrenotazioni(int contatorePrenotazioni) {
        this.contatorePrenotazioni = contatorePrenotazioni;
    }

      
}
