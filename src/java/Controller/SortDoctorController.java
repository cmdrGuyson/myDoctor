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

public class SortDoctorController extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        sortDoctors(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private void sortDoctors(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        HandleDoctor handleDoctor = new HandleDoctor();
        
        //Get user requirements for filtering and sorting
        String type = request.getParameter("type");
        String order = request.getParameter("order");
        
        //Get doctors of a certain specialization sorted according to requirement using the created HandleDoctor object
        List<Doctor> doctors = handleDoctor.sortDoctors(type, order);
        
        //set request attributes and direct user to page
        request.setAttribute("doctors", doctors);
        request.setAttribute("select", "none");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctors.jsp");
        dispatcher.forward(request, response);
    
    }
}
