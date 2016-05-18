package org.psm.mp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.psm.mp.dao.PrenotazioneDAO;
import org.psm.mp.dao.UtenteDAO;
import org.psm.mp.models.Utente;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class ProfiloController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO;
    private PrenotazioneDAO prenotazioneDAO;
    
    public void init(ServletConfig config) throws ServletException{
        // Eseguito alla creazione della servlet
        // usato per creare o caricare dati utilizzati durante la vita della servlet
        // o per eseguire una elaborazione una tantum
        ServletContext context = config.getServletContext();
        
        utenteDAO = new UtenteDAO(context);
        
        prenotazioneDAO = new PrenotazioneDAO(context);
        
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newUrl = "";
        String azione = request.getParameter("azione");
        HttpSession session = request.getSession(false);
        Utente utente = (Utente)session.getAttribute("utente");
        
       
        if(azione != null){
            if(azione.equalsIgnoreCase("modifica")){
                if(utente != null){
                    newUrl = "changeProfile.jsp";
                }else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }else{
                session.setAttribute("listaPrenotazioni", prenotazioneDAO.getPrenotazioniByIdUtente(utente.getId()));
                newUrl = "profile.jsp";
            }
        }else{
            
            session.setAttribute("listaPrenotazioni", prenotazioneDAO.getPrenotazioniByIdUtente(utente.getId()));
            newUrl = "profile.jsp";
        }

        RequestDispatcher view = request.getRequestDispatcher("/jsp/"+newUrl);
        view.forward(request, response);
        

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Utente utente = new Utente();
        
        
        utente.setNome(request.getParameter("nome"));
        utente.setCognome(request.getParameter("cognome"));
        utente.setUsername(request.getParameter("username"));
        utente.setPassword(request.getParameter("password"));
        utente.setEmail(request.getParameter("email"));
        utente.setTelefono(request.getParameter("telefono"));
        utente.setTipologiaUtenza(request.getParameter("utenza"));
        
        String id = request.getParameter("id");
        if(id != null){

            utente.setId(Integer.parseInt(id));
            utenteDAO.aggiornaUtente(utente);
            
//            session.setAttribute("msg", "Profilo aggiornato");
        }else{

            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        
        
        session.setAttribute("utente", utente);
        session.setAttribute("listaPrenotazioni", prenotazioneDAO.getPrenotazioniByIdUtente(utente.getId()));
        
//        RequestDispatcher view = request.getRequestDispatcher("Profilo");
//        view.forward(request, response);
        response.sendRedirect("Profilo");

        
    }

    
}
