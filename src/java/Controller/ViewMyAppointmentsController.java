package Controller;

import Model.Appointment;
import Model.HandleAppointment;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewMyAppointmentsController extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        getMyAppointments(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private void getMyAppointments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        //Get logged in user's email
        String email = (String) session.getAttribute("email");
        
        HandleAppointment handleAppointment = new HandleAppointment();
        
        //Get all appointments made by logged in user
        List<Appointment> appointments = handleAppointment.getAppointments(email);
    
        //Direct user to my appoinments page and display appointment information
        request.setAttribute("appointments", appointments);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("appointments.jsp");
        dispatcher.forward(request, response);
    }
}
