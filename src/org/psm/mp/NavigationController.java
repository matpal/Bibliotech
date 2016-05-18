package org.psm.mp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.psm.mp.dao.LibroDAO;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class NavigationController extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  LibroDAO libroDAO;
    
  
    public void init() throws ServletException{
        // Eseguito alla creazione della servlet
        // usato per creare o caricare dati utilizzati durante la vita della servlet
        // o per eseguire una elaborazione una tantum
        ServletContext context = getServletContext();
        
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

    	String newUrl = "";
        
//        HttpSession session = request.getSession(false);
//        Utente utente = (Utente)session.getAttribute("utente");
        
        String context = request.getContextPath();
        String url = request.getRequestURI();
        
        if(url.equalsIgnoreCase(context + "/Catalogo")){
            request.setAttribute("listaLibri",libroDAO.getAllLibri());
            newUrl = "catalogo.jsp";
        } else if(url.equalsIgnoreCase(context + "/Cerca")){
            newUrl = "search.jsp";
        } else if(url.equalsIgnoreCase(context + "/Classifiche")){
            request.setAttribute("ultimiArrivi",libroDAO.getLatestLibri());
            request.setAttribute("piuPrenotati",libroDAO.getMostRentedLibri());
            newUrl = "rankings.jsp";
        }else if(url.equalsIgnoreCase(context + "/Registrazione")){
            newUrl = "signup.jsp";
        }else if(url.equalsIgnoreCase(context + "/Login")){
            newUrl = "login.jsp";
        }
//        }else if(url.equalsIgnoreCase(context + "/Profilo")){
//            if(utente != null){
//                String action = request.getParameter("azione");
//                if("modifica".equals(action)){
//                    newUrl = "changeProfile.jsp";
//                }else{
//                    session.setAttribute("listaPrenotazioni", prenotazioneDAO.getPrenotazioniByIdUtente(utente.getId()));
//                    newUrl = "profile.jsp";
//                }
//            }else{
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            }
//            
        else if(url.equalsIgnoreCase(context + "/Libro")){
            String id = request.getParameter("id");
            if(id != null){
                request.setAttribute("libro", libroDAO.getLibro(Integer.parseInt(id)));
                newUrl ="book.jsp";
            }else{

                response.sendRedirect("Catalogo");
                return;
            }
        }
        
        RequestDispatcher view = request.getRequestDispatcher("/jsp/"+ newUrl);
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
