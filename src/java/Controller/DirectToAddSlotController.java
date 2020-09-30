package Controller;

import Model.Doctor;
import Model.HandleDoctor;
import Model.HandleHospital;
import Model.Hospital;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DirectToAddSlotController extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        HandleDoctor handleDoctor = new HandleDoctor();
        HandleHospital handleHospital = new HandleHospital();
        
        //Get a list of doctos
        List<Doctor> doctors = handleDoctor.getAllDoctors();
        
        //Get a list of hospitals
        List<Hospital> hospitals = handleHospital.getAllHospitals();
        
        //Set request attributes
        request.setAttribute("doctors", doctors);
        request.setAttribute("hospitals", hospitals);
    
        //Direct to add availability slot page
        RequestDispatcher dispatcher = request.getRequestDispatcher("add-slot.jsp");
        dispatcher.forward(request, response);
    }

}
