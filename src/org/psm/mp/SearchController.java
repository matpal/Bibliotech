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
import org.psm.mp.models.Libro;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class SearchController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LibroDAO libroDAO;
    
    
    public void init(ServletConfig config) throws ServletException{
        // Eseguito alla creazione della servlet
        // usato per creare o caricare dati utilizzati durante la vita della servlet
        // o per eseguire una elaborazione una tantum
        ServletContext context = config.getServletContext();
        
        libroDAO = new LibroDAO(context);

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
        
        String query = request.getParameter("query");
        String category = request.getParameter("category");
        
        
       
        
        if(  (query != null) &&  (!query.equals(""))  ){
            List<Libro> libri = libroDAO.getLibriBy(category, query);
            if(libri.isEmpty()){
                request.setAttribute("msg","Nessun libro trovato.");
            }else{
                request.setAttribute("risultatoRicerca", libri);
            } 

            request.setAttribute("isTitolo", "");
            request.setAttribute("isAutore", "");
            request.setAttribute("isGenere", "");
            
            if(category.equalsIgnoreCase("titolo")){
                request.setAttribute("isTitolo","selected=selected");
            }if(category.equalsIgnoreCase("autore")){
                request.setAttribute("isAutore","selected=selected");
            }if(category.equalsIgnoreCase("genere")){
                request.setAttribute("isGenere","selected=selected");
            }
            
            request.setAttribute("query",query);
            
        } else {
            request.setAttribute("msg", "Inserire un termine per la ricerca");
        }

        
        RequestDispatcher view = request.getRequestDispatcher("/jsp/search.jsp");
        view.forward(request, response);
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
