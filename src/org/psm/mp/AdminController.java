package org.psm.mp;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.psm.mp.dao.LibroDAO;
import org.psm.mp.dao.PrenotazioneDAO;
import org.psm.mp.dao.UtenteDAO;
import org.psm.mp.models.Libro;
import org.psm.mp.models.Prenotazione;
import org.psm.mp.models.Utente;
import org.psm.mp.models.Utente.Utenza;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class AdminController extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  LibroDAO libroDAO;
    private  UtenteDAO utenteDAO;
    private  PrenotazioneDAO prenotazioneDAO;
    
   
    public void init(ServletConfig config) throws ServletException{
        // Eseguito alla creazione della servlet
        // usato per creare o caricare dati utilizzati durante la vita della servlet
        // o per eseguire una elaborazione una tantum
        ServletContext context = config.getServletContext();
        
        libroDAO = new LibroDAO(context);
        prenotazioneDAO = new PrenotazioneDAO(context);
        utenteDAO = new UtenteDAO(context);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String catParam = request.getParameter("category");
        String azione = request.getParameter("action");
        String id = request.getParameter("id");
        String destination = "";
        
        
        if(catParam != null){
           
            
            // LIBRI
            if(catParam.equalsIgnoreCase("libri")){
                
                if(azione.equalsIgnoreCase("lista")){
                    request.setAttribute("listaLibri", libroDAO.getAllLibri());
                    destination ="/jsp/admin-books.jsp";
                }
                
                // ELIMINA
                else if(azione.equalsIgnoreCase("elimina")){
                    if(id != null){
                        
                        libroDAO.rimuoviLibro(Integer.parseInt(id));
                        ArrayList<Prenotazione> prenotazioni = prenotazioneDAO.getPrenotazioniByIdLibro(Integer.parseInt(id));
                        int i = 0;
                        while(i < prenotazioni.size()){
                            Prenotazione p = (Prenotazione)prenotazioni.get(i);
                            prenotazioneDAO.rimuoviPrenotazione(p.getId());
                            i++;
                        }
                        request.setAttribute("msg", "Libro eliminato");
                        destination = "Admin?action=lista&category=libri";

                    } else {
                        request.setAttribute("msg", "");
                        destination ="Admin?action=lista&category=libri";
                    } 
                }
                // MODIFICA
                else if(azione.equalsIgnoreCase("modifica")){
                    destination ="/jsp/newBook.jsp";
                    
                    Libro libro = libroDAO.getLibro(Integer.parseInt(id));
                    request.setAttribute("libro",libro);
                }
                // INSERISCI
                else if(azione.equalsIgnoreCase("inserisci")){
                    destination ="/jsp/newBook.jsp";
                } 


            }
            // UTENTI
            else if (catParam.equalsIgnoreCase("utenti")){
                HttpSession session = request.getSession(false);
                Utente utenteSessione = (Utente)session.getAttribute("utente");
                
                if(azione.equalsIgnoreCase("lista")){
                    request.setAttribute("listaUtenti", utenteDAO.getAllUtenti());
                    destination ="/jsp/admin-users.jsp";
                }
                
                else if(azione.equalsIgnoreCase("elimina")){
                    if(id != null){
                        
                        
                        if(Integer.parseInt(id) != utenteSessione.getId()){
                            utenteDAO.rimuoviUtente(Integer.parseInt(id));
                            request.setAttribute("msg", "Utente eliminato");
                            destination = "Admin?action=lista&category=utenti";
                        }else{
                            request.setAttribute("msg", "Non puoi eliminare te stesso");
                            destination = "Admin?action=lista&category=utenti";
                        }
                        
                        

                    } else {
                        // torna alla lista utenti
                        request.setAttribute("msg", "");
                        destination ="Admin?action=lista&category=utenti";
                    } 
                }
                else if(azione.equalsIgnoreCase("promuovi")){
                    if(id != null){
                        
                        Utente utenteDB = utenteDAO.getUser(Integer.parseInt(id));
                        // controlla che l'utente non sia lo stesso
                        if(utenteSessione.getId() == utenteDB.getId()){
                            request.setAttribute("msg","Non puoi promuovere te stesso");
                            destination ="Admin?action=lista&category=utenti";
                        }else{
                            //controlla che l'utente non sia gia' un bibliotecario
                            if(utenteDB.getTipologiaUtenza().equals(Utenza.BIBLIOTECARIO)){
                                request.setAttribute("msg","L'utente "+utenteDB.getUsername() +" &egrave; gi&agrave; un Bibliotecario");
                                destination ="Admin?action=lista&category=utenti";
                            }else{
                                utenteDB.setTipologiaUtenza("BIBLIOTECARIO");
                                utenteDAO.aggiornaUtente(utenteDB);
                                request.setAttribute("msg", "Utente '"+utenteDB.getUsername()+"' promosso a Bibliotecario");
                                destination ="Admin?action=lista&category=utenti";
                            }
                        }
                        
                    } else {
                        // torna alla lista utenti
                        request.setAttribute("msg", "");
                        destination ="Admin?action=lista&category=utenti";
                    } 
                }

            }
            // PRENOTAZIONI
            else if ("prenotazioni".equals(catParam)){
                request.setAttribute("listaPrenotazioni", prenotazioneDAO.getAllPrenotazioni());
                destination = "/jsp/admin-prenotazioni.jsp";
            }
            
            RequestDispatcher view = request.getRequestDispatcher(destination);
            view.forward(request, response);

        }else{
            RequestDispatcher view = request.getRequestDispatcher("/jsp/admin.jsp");
            view.forward(request, response);

        } 
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String catParam = request.getParameter("category");
        String destination = "";
        String error = "";
        
        
        if(catParam.equalsIgnoreCase("libri")){
            
            Libro libro = new Libro();
            
            String isbn = request.getParameter("isbn");
            if( isbn == null || isbn.equals("")){
                error += "Campo isbn vuoto<br>";
            }
            
            String titolo = request.getParameter("titolo");
            if( titolo == null || titolo.equals("")){
                error += "Campo titolo vuoto<br>";
            }
            
            String autore = request.getParameter("autore");
            if( autore == null || autore.equals("")){
                error += "Campo autore vuoto<br>";
            }
            
            String editore = request.getParameter("editore");
            if( editore == null || editore.equals("")){
                error += "Campo editore vuoto<br>";
            }
            
            String genere = request.getParameter("genere");
            if( genere == null || genere.equals("")){
                error += "Campo genere vuoto<br>";
            }
            
            String quantita = request.getParameter("quantita");
            int q = 0;
            if( quantita == null || quantita.equals("")){
                error += "Campo quantit&agrave; vuoto<br>";
            }else{
                try{
                    q = Integer.parseInt(request.getParameter("quantita"));
                }catch(NumberFormatException e){
                    error +="Campo quantit&agrave; non valido.<br>";
                }
                
                if( q < 0){
                    error += "Campo quantit&agrave; negativo<br>";
                }
            }
            
            String libroId = request.getParameter("libroid");
            
            // se non ci sono errori ...
            if(error.equals("")){
                libro.setIsbn(isbn);
                libro.setTitolo(titolo);
                libro.setAutore(autore);
                libro.setEditore(editore);
                libro.setGenere(genere);
                libro.setQuantita(q);



                

                if(libroId == null || libroId.isEmpty()){

                    libro.setContatorePrenotazioni(0);
                    
                    if(libroDAO.inserisciLibro(libro))
                        error += "Libro inserito";
                    else
                        error += "Errore durante l'inserimento";
                    
                } else {
                    libro.setId(Integer.parseInt(libroId));

                    libro.setContatorePrenotazioni(Integer.parseInt(request.getParameter("contatorePrenotazioni")));
                    libroDAO.aggiornaLibro(libro);

                    error += "Libro aggiornato";
                }
                request.setAttribute("listaLibri", libroDAO.getAllLibri());
                destination = "/jsp/admin-books.jsp";
            }else{
                
                // ricarica la pagina di inserimento
                if(libroId == null || libroId.isEmpty()){
                    destination ="Admin?action=inserisci&category=libri";
                }
                // ricarica la pagina di modifica
                else{
                    destination="Admin?action=modifica&category=libri&id="+request.getParameter("libroid");
                }
                response.sendRedirect(destination);
                return;
                
            }
            
        }
        request.setAttribute("msg", error);
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
       
    }

}
