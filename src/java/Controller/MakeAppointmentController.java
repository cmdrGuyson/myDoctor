package Controller;

import Model.Appointment;
import Model.AvailabilitySlot;
import Model.HandleAppointment;
import Model.HandleAvailability;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MakeAppointmentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        addAppointment(request, response);

    }

    private void addAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get the availability slotID for which the appointment is made
        int availabilitySlotID = Integer.parseInt(request.getParameter("availablitySlotID"));
        
        HandleAvailability handleAvailability = new HandleAvailability();
        HandleAppointment handleAppointment = new HandleAppointment();
        
        //Get availability slot information using availabilitySlotID
        AvailabilitySlot slot = handleAvailability.getAvailabilitySlot(availabilitySlotID);
        
        HttpSession session = request.getSession();
        
        //Get logged in user's email
        String email = (String) session.getAttribute("email");
        
        //Make the appointment
        handleAppointment.addAppointment(new Appointment(availabilitySlotID, email));
        
        //Set request attributes
        request.setAttribute("slot", slot);
        
        //Direct the user to success page where all appointment information is shown
        RequestDispatcher dispatcher = request.getRequestDispatcher("util/success_appointment.jsp");
        dispatcher.forward(request, response);
    }
}
