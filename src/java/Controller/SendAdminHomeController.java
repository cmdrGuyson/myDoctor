package Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Facade pattern to direct users to respective home page according to user type
public class SendAdminHomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sendAdminHome(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Servlets are incapable of calling a doGet from doPost therefore handleSendHome is called in all types of requests
        sendAdminHome(request, response);
    }
    
    private void sendAdminHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("home-admin.jsp");
        dispatcher.forward(request, response);

    }
}
