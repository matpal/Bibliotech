package org.psm.mp.models;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class Utente {
    private int    id;
    private String username;
    private String password;
    private String email;
    private String nome;
    private String cognome;
    private String telefono;
    public enum Utenza{
        CLIENTE, BIBLIOTECARIO
    };
    private Utenza tipologiaUtenza;

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public Utenza getTipologiaUtenza() {
        return tipologiaUtenza;
    }

    public void setTipologiaUtenza(Utenza tipologiaUtenza) {
        this.tipologiaUtenza = tipologiaUtenza;
    }
    
    public void setTipologiaUtenza(String tipologiaUtenza){
        this.tipologiaUtenza = Utenza.valueOf(tipologiaUtenza.toUpperCase().trim());
    }
    
}
