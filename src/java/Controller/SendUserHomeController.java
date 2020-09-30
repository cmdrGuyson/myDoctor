package Controller;

import Model.BarChartView;
import Model.CovidDataSubject;
import Model.PieChartView;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Facade pattern to direct users to respective home page according to user type
public class SendUserHomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sendUserHome(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Servlets are incapable of calling a doGet from doPost therefore handleSendHome is called in all types of requests
        sendUserHome(request, response);
    }

    private void sendUserHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BarChartView barChartView = new BarChartView();
        PieChartView pieChartView = new PieChartView();

        CovidDataSubject covidDataSubject = new CovidDataSubject();
        covidDataSubject.registerObserver(pieChartView);
        covidDataSubject.registerObserver(barChartView);

        String country = "global";

        covidDataSubject.setCountry(country);

        request.setAttribute("pieChartView", pieChartView);
        request.setAttribute("barChartView", barChartView);
        request.setAttribute("country", country);

        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);

    }

}
