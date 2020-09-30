package Controller;

import Model.HandleHospital;
import Model.Hospital;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAllHospitalsController extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getHospitals(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getHospitals(request, response);
        
    }
    
    private void getHospitals(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        HandleHospital handleHospital = new HandleHospital();
        
        //Get all hospitals using HandleHospital object
        List<Hospital> hospitals = handleHospital.getAllHospitals();
        
        //Set request attributes
        request.setAttribute("hospitals", hospitals);
        
        //Direct user to hospitals page
        RequestDispatcher dispatcher = request.getRequestDispatcher("hospitals.jsp");
        dispatcher.forward(request, response);
    
    }
}
