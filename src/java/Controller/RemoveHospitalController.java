package Controller;

import Model.HandleHospital;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveHospitalController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        removeHospital(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void removeHospital(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HandleHospital handleHospital = new HandleHospital();

        //Get hospital to be removed
        int hospitalID = Integer.parseInt(request.getParameter("hospitalID"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAllHospitalsController");

        //Remove the hospital by calling method removeHospital()
        try {
            handleHospital.removeHospital(hospitalID);
        } 
        //If hospital has any doctors registered to it
        catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            request.setAttribute("message", "This hospital is associated with doctors in the system!");
            dispatcher = request.getRequestDispatcher("util/general_error.jsp");
        }

        dispatcher.forward(request, response);

    }
}
