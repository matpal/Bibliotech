package org.psm.mp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.psm.mp.dao.UtenteDAO;
import org.psm.mp.models.Utente;

/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class LoginController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
        
        Utente utenteDb;
        // Recupera le credenziali di accesso
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String error = "";
        
//        boolean rememberMe = "on".equals(request.getParameter("rememberMe"));
        
        UtenteDAO dao = new UtenteDAO(getServletContext());
        
        // Controlla che i campi username e password non siano vuoti
        if( username == null || username.equals("")){
            error += "Campo username vuoto<br>";
        }
        if(password == null || password.equals("")){
            error += "Campo password vuoto<br>";
        }
        if( (username != null) || (password !=null) ){
            
            // raccoglie le informazioni dell'utente dal database
            utenteDb = dao.getUser(username, password);
            
            if (utenteDb != null) {
           
                boolean result = dao.authenticate(utenteDb, username, password);

                if(result)
                {
                    // Crea una nuova sessione 
                    HttpSession session = request.getSession(true);

                    //salvo in sessione l'oggetto user
                    session.setAttribute("utente", utenteDb);


                    /*----------Remember Me----------*/
//   
//                    if(rememberMe){
//
//                            Cookie ckRememberMe = new Cookie("BT-RememberMe","true");
//                            ckRememberMe.setMaxAge(60*60*24);
//                            response.addCookie(ckRememberMe);
//
//                            Cookie ckUid = new Cookie("BT-Uid",utenteDb.getUid());
//                            ckUid.setMaxAge(60*60*24);
//                            response.addCookie(ckUid);
//
//                    }
//                    else{
//                        // Crea cookie per l'user id
//                        Cookie ckUid = new Cookie("BT-Uid",utenteDb.getUid());
//                        ckUid.setMaxAge(60*60*24);
//                        response.addCookie(ckUid);
//
//                        // Elimina il cookie "ricordami"
//                        Cookie ck = new Cookie("BT-RememberMe","");
//                        ck.setMaxAge(0);
//                        response.addCookie(ck);
//                    }
                    /*-----------------------------------*/
                }

                //necessario per cambiare URL nella barra indirizzi
                response.sendRedirect("Home");
                return;
            }else{
                if(error.equals(""))
                    error = "Errore durante il login. Controlla che Username e Password siano corretti.";

            }
            
            
        }
        request.setAttribute("msg", error);
        RequestDispatcher view = request.getRequestDispatcher("Login");
        view.forward(request,response);
        
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
