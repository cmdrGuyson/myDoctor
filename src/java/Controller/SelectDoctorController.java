package Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Helper controller to notify user to select a doctor when making an appointment
public class SelectDoctorController extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("select", "select");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAllDoctorsController");
        dispatcher.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
