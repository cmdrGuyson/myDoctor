package Controller;

import Model.HandleAvailability;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveAvailabilityController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        removeSlot(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void removeSlot(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HandleAvailability handleAvailability = new HandleAvailability();

        //Get slot to be removed
        int slotID = Integer.parseInt(request.getParameter("availablitySlotID"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAllAvailabilitySlotsController");

        //Remove the slot by calling respective method on handleAvailability object
        try {
            handleAvailability.removeAvailabilitySlot(slotID);
        } 
        
        catch (MySQLIntegrityConstraintViolationException e) {
            //If availability slots has any appointments linked to it direct user to error page
            System.out.println(e);
            request.setAttribute("message", "This availability slot has appointments linked to it!");
            dispatcher = request.getRequestDispatcher("util/general_error.jsp");
        }

        dispatcher.forward(request, response);
    }
}
