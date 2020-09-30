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

public class GetCovidStatsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getCovidStats(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void getCovidStats(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Instantiate BarChartView and PieChartView observers
        BarChartView barChartView = new BarChartView();
        PieChartView pieChartView = new PieChartView();

        //Instantiate CovidDataSubject (Subject) and register the observers
        CovidDataSubject covidDataSubject = new CovidDataSubject();
        covidDataSubject.registerObserver(pieChartView);
        covidDataSubject.registerObserver(barChartView);

        //Get the country for which covid19 statistics are to be taken
        String country = (String) request.getParameter("country");
        
        //Changing the country in covidDataSubject will change the statisics contained in the object. This in turn will update the observers.
        covidDataSubject.setCountry(country);

        //Set attributes to be sent to the jsp
        request.setAttribute("pieChartView", pieChartView);
        request.setAttribute("barChartView", barChartView);
        request.setAttribute("country", country);
        
        //Forward the request
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }
}
