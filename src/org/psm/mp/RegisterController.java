package org.psm.mp;

import org.psm.mp.dao.UtenteDAO;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.psm.mp.models.Utente;


/**
 *
 * @author Matteo Palumbo <matteopalumbo at mail.com>
 */
public class RegisterController extends HttpServlet {

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
        
        System.out.println("Inside RegisterController");
        String destination = "";
        String error = "";
//        int ONE_MINUTE = 60;
//        int ONE_HOUR = 60 * ONE_MINUTE;
//        int ONE_DAY = 24 * ONE_HOUR;

        Utente u = new Utente();
        
        
        String username = request.getParameter("username");
        if( username == null || username.equals("")){
            error += "Campo username vuoto<br>";
        }
        
        String email = request.getParameter("email");
        if( email == null || email.equals("")){
            error += "Campo email vuoto<br>";
        }else{
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if(!matcher.matches())
                error += "Campo email non valido. Deve essere del tipo mail@example<br>";
        }
        
        
        String password = request.getParameter("password");
        if( password == null || password.equals("")){
            error += "Campo password vuoto<br>";
        }
        
        String confermaPassword = request.getParameter("conferma-password");
        if( confermaPassword == null || confermaPassword.equals("")){
            error += "Campo conferma Password vuoto<br>";
        }
        if(!password.equals(confermaPassword)){
            error += "I campi password e conferma password non coincidono<br>";
        }
        
        String nome = request.getParameter("nome");
        if( nome == null || nome.equals("")){
            error += "Campo nome vuoto<br>";
        }
        
        String cognome = request.getParameter("cognome");
        if( cognome == null || cognome.equals("")){
            error += "Campo cognome vuoto<br>";
        }
        
        String telefono = request.getParameter("telefono");
        if( telefono == null || telefono.equals("")){
            error += "Campo telefono vuoto<br>";
        }
        
        String utenza = request.getParameter("utenza");
        if( utenza == null || utenza.equals("")){
            error += "Campo utenza vuoto<br>";
        }
        
        
        
        // se non ci sono errori..
        if(error.equals("")){
//            try{
//                u.setUid(URLEncoder.encode(UUID.randomUUID().toString(), "UTF-8"));
//            } catch(UnsupportedEncodingException e){
//                Logger.getLogger(UtenteDAO.class.getName()).log(Logger.Level.ERROR,null, e);
//            }

            u.setUsername(username);
            u.setEmail(email);
            u.setPassword(password);


            u.setNome(nome);
            u.setCognome(cognome);
            u.setTelefono(telefono);
            u.setTipologiaUtenza(utenza);

            UtenteDAO dao = new UtenteDAO(getServletContext());
            boolean result = dao.inserisciUtente(u);

            if(result){
//                Cookie userCookie = new Cookie("BT-Uid",u.getUid());
//                userCookie.setMaxAge(ONE_DAY * 30);
//                response.addCookie(userCookie);
                HttpSession session = request.getSession(true);
                session.setAttribute("utente", u);

                destination="/jsp/success.jsp";
            }else{
                response.sendRedirect("Registrazione");
                return;
            }

            
            
        } else{
            destination = "Registrazione";
        }
        request.setAttribute("msg", error);
        RequestDispatcher view = request.getRequestDispatcher(destination);
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
