/*
 * Jason Arnold
 * CIST 2373 JAVA 3
 * Semester Project Dentist Login Servlet
 */
package DentistWebApp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DentistLoginServlet", urlPatterns = {"/DentistLoginServlet"})
public class DentistLoginServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String login;
        String pass;
        try (PrintWriter out = response.getWriter()) {
            //get login information from HTML
            login = request.getParameter("DenID");
            pass = request.getParameter("Pword");
            
            //create dentistt
            Dentist d1 = new Dentist();
            d1.selectDB(login);
            
            //add dentist d1 to session
            HttpSession session1;
            session1 = request.getSession();
            session1.setAttribute("d1", d1);
            
            //compare retrieved form data to database
            if (pass.equals(d1.getPassword()))
            {
                //success, pass to dentist display page
                RequestDispatcher rd = request.getRequestDispatcher("/DisplayDentist.jsp");
                rd.forward(request, response);
            }
            else
            {
                //failure, pass to error page
                RequestDispatcher rd = request.getRequestDispatcher("/DentistLoginError.jsp");
                rd.forward(request, response);  
            }
            
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
