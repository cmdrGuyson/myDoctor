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

public class ViewAllAvailabilitySlotsController extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        getAllSlots(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        getAllSlots(request, response);
    }
    
    private void getAllSlots(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        HandleAvailability handleAvailability = new HandleAvailability();
        
        //Get all availability slots from created object
        List<AvailabilitySlot> slots = handleAvailability.getAllAvailabilitySlots();
        
        //Set request attributes and direct user to slots page
        request.setAttribute("slots", slots);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("slots.jsp");
        dispatcher.forward(request, response);
    
    }

}
