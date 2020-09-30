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

public class ViewAllAppointmentsController extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        getAppointments(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private void getAppointments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HandleAppointment handleAppointment = new HandleAppointment();
        
        //Get all appointments in the system
        List<Appointment> appointments = handleAppointment.getAppointments();
        
        //Set request attributes
        request.setAttribute("appointments", appointments);
        
        //Direct user to view all appointments page
        RequestDispatcher dispatcher = request.getRequestDispatcher("appointments.jsp");
        dispatcher.forward(request, response);
    
    }
}
