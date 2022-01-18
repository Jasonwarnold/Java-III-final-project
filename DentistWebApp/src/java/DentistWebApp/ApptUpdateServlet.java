/*
 * Jason Arnold
 * CIST 2373 JAVA 3
 * Semester Project AppointmentUpdate Servlet
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

@WebServlet(name = "ApptUpdateServlet", urlPatterns = {"/ApptUpdateServlet"})
public class ApptUpdateServlet extends HttpServlet {

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
        String datetime;
        String patient;
        String dentist;
        String proc;
        
        try (PrintWriter out = response.getWriter()) {
            
         //get data from jsp
         datetime = request.getParameter("date");
         patient = request.getParameter("patient");
         dentist = request.getParameter("dentist");
         proc = request.getParameter("proc");
         
         //new appointment
         Appointment a1 = new Appointment();  
         
         //fill attributes from jsp
         a1.setDateTime(datetime);                                                
         a1.setPatient(patient);
         a1.setDentist(dentist);
         a1.setProcedure(proc);
         a1.display();
         
         //new appointment from database used to determine if existing appointment
         Appointment a2 = new Appointment();
         a2.selectDB(patient);
         a2.display();
         
         //if database appointment is empty, returns null in all but patient field
         if (a2.getDateTime().equals(""))
         {
             
             a1.insertDB(datetime, patient, dentist, proc);
         }
         else
         {
             //update database
         a1.updateDB(datetime, patient, dentist, proc); 
         }
             
         
                
         //new patient and fill with opdated info to pass to JSP
         Patient p1 = new Patient();                                                                
         p1.selectDB(a1.getPatient());
         
         //add patient p1 to session
        HttpSession session1;
        session1 = request.getSession();
        session1.setAttribute("p1", p1);
        //forward control back to patient display    
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
