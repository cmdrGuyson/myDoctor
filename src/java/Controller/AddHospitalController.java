package Controller;

import Model.HandleHospital;
import Model.Hospital;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddHospitalController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        addHospital(request, response);

    }

    private void addHospital(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get user input from request
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        
        //Add hospital to the system
        HandleHospital handleHospital = new HandleHospital();
        handleHospital.addHospital(new Hospital(name, address));
        
        //Redirect user to doctors page
        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAllHospitalsController");
        dispatcher.forward(request, response);
    }
}
