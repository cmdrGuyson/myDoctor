package Controller;

import Model.Doctor;
import Model.HandleDoctor;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAllDoctorsController extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        getAllDoctors(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private void getAllDoctors(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        HandleDoctor handleDoctor = new HandleDoctor();
        
        //Get all doctors using the created HandleDoctor object
        List<Doctor> doctors = handleDoctor.getAllDoctors();
        
        //If coming from <Make appointment> notify user to select a doctor for the appointment
        String select = (request.getAttribute("select")==null) ? "none" : "select";
        
        //Set request attributes and direct user to doctors.jsp
        request.setAttribute("doctors", doctors);
        request.setAttribute("select", select);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctors.jsp");
        dispatcher.forward(request, response);
    
    }

}
