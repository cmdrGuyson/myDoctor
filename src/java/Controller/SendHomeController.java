package Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Facade pattern to direct users to respective home page according to user type
public class SendHomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleSendHome(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Servlets are incapable of calling a doGet from doPost therefore handleSendHome is called in all types of requests
        handleSendHome(request, response);
    }

    private void handleSendHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accountType = "";

        // Session attribute of typeOfUser is taken and checked if user is logged in
        if (request.getSession().getAttribute("typeOfUser") != null) {
            accountType = (String) request.getSession().getAttribute("typeOfUser");

            //User is directed to their respective controller to be sent to home page
            switch (accountType) {
                case "admin":
                    response.sendRedirect("SendAdminHomeController");
                    break;
                case "user":
                    response.sendRedirect("SendUserHomeController");
                    break;
            }

        } else {
            //If user is not logged in, they are directed to default home page
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }

}
