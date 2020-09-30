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

public class SearchDoctorController extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        searchDoctors(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private void searchDoctors(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        HandleDoctor handleDoctor = new HandleDoctor();
        
        //Get user entered search term
        String searchKey = request.getParameter("searchKey");
        
        //Search doctors using the search key and set as request attributes
        List<Doctor> doctors = handleDoctor.searchDoctor(searchKey);
        
        request.setAttribute("doctors", doctors);
        request.setAttribute("select", "none");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctors.jsp");
        dispatcher.forward(request, response);
    
    }
}
