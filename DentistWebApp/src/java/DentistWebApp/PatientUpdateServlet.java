/*
 * Jason Arnold
 * CIST 2373 JAVA 3
 * Semester Project Patient Update Servlet
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

@WebServlet(urlPatterns = {"/PatientUpdateServlet"})
public class PatientUpdateServlet extends HttpServlet {

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
        
        String patID;
        String pass;
        String fname;
        String lname;
        String address;
        String email;
        String insco;
        
        try (PrintWriter out = response.getWriter()) {
            
           
            //pull patient info from Display Patient jsp
            Patient p1 = new Patient();
            patID = request.getParameter("patId");
            pass = request.getParameter("password");
            fname = request.getParameter("fName");
            lname = request.getParameter("lName");
            address = request.getParameter("address");
            email = request.getParameter("email");
            insco = request.getParameter("insurance");
            
            //update Patient in database
            p1.updateDB(patID, pass, fname, lname, address, email, insco);
            
            //retrieve updated patient info to forward back to Display Patient JSP
            p1.selectDB(patID);
            HttpSession session1;
            session1 = request.getSession();
            session1.setAttribute("p1", p1);
            
            //orward control back to Display Patient
            RequestDispatcher rd = request.getRequestDispatcher("/DisplayPatient.jsp");
                rd.forward(request, response);
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
