package Controller;

import Model.AvailabilitySlot;
import Model.HandleAvailability;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAvailabilityController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        addSlot(request, response);

    }

    private void addSlot(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get user input from request
        int doctorID = Integer.parseInt(request.getParameter("doctor"));
        int hospitalID = Integer.parseInt(request.getParameter("hospital"));
        String date = request.getParameter("date");
        String time = request.getParameter("time");

        HandleAvailability handleAvailability = new HandleAvailability();

        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAllAvailabilitySlotsController");
        
        try {
            handleAvailability.addAvailabilitySlot(new AvailabilitySlot(doctorID, hospitalID, date, time));
        } catch (MySQLIntegrityConstraintViolationException e) {
            //If the availability slot already exists on the system user will be directed to an error page
            System.out.println(e);
            request.setAttribute("message", "The availability slot you are trying to create already exists!");
            dispatcher = request.getRequestDispatcher("util/general_error.jsp");
        }

        dispatcher.forward(request, response);
    }
}
