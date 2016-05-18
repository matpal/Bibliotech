package org.psm.mp;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.psm.mp.dao.LibroDAO;
import org.psm.mp.dao.PrenotazioneDAO;
import org.psm.mp.models.Libro;
import org.psm.mp.models.Prenotazione;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class PrenotazioneController extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrenotazioneDAO prenotazioneDAO;
    private LibroDAO libroDAO;
    
    
    public void init(ServletConfig config) throws ServletException{
        // Eseguito alla creazione della servlet
        // usato per creare o caricare dati utilizzati durante la vita della servlet
        // o per eseguire una elaborazione una tantum
        ServletContext context = config.getServletContext();
        
        libroDAO = new LibroDAO(context);
        prenotazioneDAO = new PrenotazioneDAO(context);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Prenotazione prenotazione = new Prenotazione();
        
        String idUtente = request.getParameter("idUtente");
        String idLibro = request.getParameter("idLibro");
        String action = request.getParameter("azione");
        String destination = "";
        
        
        if(action != null){
            if("prenota".equals(action)){

                if(idUtente != null && idLibro != null){

                    prenotazione.setIdUtente(Integer.parseInt(idUtente));
                    prenotazione.setIdLibro(Integer.parseInt(idLibro));


                    Libro libro = libroDAO.getLibro(Integer.parseInt(idLibro));
                    int quantita = libro.getQuantita();
                    int contatore = libro.getContatorePrenotazioni();

                    List<Prenotazione> prenotazioniAttive = prenotazioneDAO.getPrenotazioniByIdUtente(Integer.parseInt(idUtente));

                    if(quantita > 0){
                        if(prenotazioniAttive.size() < 3){
                            boolean result = prenotazioneDAO.inserisciPrenotazione(prenotazione);
                            if(result){
                                quantita--;
                                libro.setQuantita(quantita);
                                contatore++;
                                libro.setContatorePrenotazioni(contatore);
                                libroDAO.aggiornaLibro(libro);
                                request.setAttribute("msg", "Libro prenotato!");
                            }else{
                                request.setAttribute("msg", "Hai già prenotato questo libro!");
                            }
                        }else{
                            request.setAttribute("msg", "Hai già raggiunto il limite massimo di prenotazioni!");
                        }
                     }else{
                        request.setAttribute("msg", "Non ci sono abbastanza copie!");
                    }

                    destination = "Libro?id="+idLibro;
                    RequestDispatcher view = request.getRequestDispatcher(destination);
                    view.forward(request, response);
                }
            }else if("consegna".equals(action)){
                if(idUtente != null && idLibro != null){


                    prenotazione = prenotazioneDAO.getPrenotazione(Integer.parseInt(idUtente), Integer.parseInt(idLibro));
                    prenotazioneDAO.rimuoviPrenotazione(prenotazione.getId());

                    Libro libro = libroDAO.getLibro(Integer.parseInt(idLibro));
                    int quantita = libro.getQuantita();
                    quantita++;
                    libro.setQuantita(quantita);
                    libroDAO.aggiornaLibro(libro);
                    destination = "Profilo";
                }
                response.sendRedirect(destination);
                return;
            }
            
            
        }else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } 
        

        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
