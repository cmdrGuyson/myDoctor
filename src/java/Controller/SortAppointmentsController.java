package Controller;

import Model.Appointment;
import Model.HandleAppointment;
import Model.SortAppointmentAscending;
import Model.SortAppointmentDescending;
import Model.SortAppointmentModule;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SortAppointmentsController extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        sortAppointments(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private void sortAppointments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        //Get email and type of user
        String email = (String) session.getAttribute("email");
        String type = (String) session.getAttribute("typeOfUser");
        
        //Sorting order
        String order = request.getParameter("order");
        
        HandleAppointment handleAppointment = new HandleAppointment();
        
        List<Appointment> appointments;
        
        if(type.equals("admin")){
            //Get all apointments if admin
            appointments = handleAppointment.getAppointments();
        }else{
            //Else get only user's appointments
            appointments = handleAppointment.getAppointments(email);
        }
        
        //Stratergy pattern is used to sort appointments
        SortAppointmentModule context = new SortAppointmentModule();
        
        if(order.equals("ascending")){
            //If appointments should be sorted in ascending order set the stratergy as SortAppointmentAscending class
            context.setSortAppointmentStratergy(new SortAppointmentAscending());
        }else{
            //If appointments should be sorted in descending order set the stratergy as SortAppointmentDescending class
            context.setSortAppointmentStratergy(new SortAppointmentDescending());
        }
    
        //Sort the appointments using context object and proper stratergy
        appointments = context.sortAppointments(appointments);
        
        
        //Set request attributes and direct the user to view appointments page
        request.setAttribute("appointments", appointments);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("appointments.jsp");
        dispatcher.forward(request, response);
    }

}
