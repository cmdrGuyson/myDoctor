package Controller;

import Model.HandleDoctor;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveDoctorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        removeDoctor(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void removeDoctor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HandleDoctor handleDoctor = new HandleDoctor();

        //Get doctor to be removed
        int doctorID = Integer.parseInt(request.getParameter("doctorID"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAllDoctorsController");

        //Remove the doctor by calling removeDoctor() method
        try {
            handleDoctor.removeDoctor(doctorID);
        } 
        //If doctor has availability slotss
        catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            //This message will be displayed on the error page
            request.setAttribute("message", "This doctor has availability slots!");
            dispatcher = request.getRequestDispatcher("util/general_error.jsp");
        }

        dispatcher.forward(request, response);

    }

}
