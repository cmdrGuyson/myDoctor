package Controller;

import Model.AvailabilitySlot;
import Model.HandleAvailability;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAvailableSlotsController extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        getAllSlots(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private void getAllSlots(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        HandleAvailability handleAvailability = new HandleAvailability();
        
        //Get doctorID
        int doctorID = Integer.parseInt(request.getParameter("doctorID"));
        
        //Get all availability slots of the doctor
        List<AvailabilitySlot> slots = handleAvailability.getAllAvailabilitySlots(doctorID);
        
        //Set request attributes
        request.setAttribute("slots", slots);
        request.setAttribute("size", slots.size());
        
        //Direct user to select slot page
        RequestDispatcher dispatcher = request.getRequestDispatcher("select-slot.jsp");
        dispatcher.forward(request, response);
    
    }

}
