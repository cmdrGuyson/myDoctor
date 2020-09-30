package Model;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ProxyDirector implements Director {

    private final ServletRequest request;
    private final ServletResponse response;
    private final FilterChain chain;
    private AuthenticatedDirector authenticatedDirector;

    public ProxyDirector(ServletRequest request, ServletResponse response, FilterChain chain) {
        this.request = request;
        this.response = response;
        this.chain = chain;
    }

    @Override
    public void directUser() {
        //Cast the ServletRequest to an HttpServletRequest
        HttpServletRequest http_request = (HttpServletRequest) request;

        try {

            //Get type of user
            String type = (String) http_request.getSession().getAttribute("typeOfUser") == null ? null : (String) http_request.getSession().getAttribute("typeOfUser");

            if (type == null || !type.equals("admin")) {

                //If typeOfUser is not admin forward request to access-denied page
                RequestDispatcher dispatcher = request.getRequestDispatcher("util/access-denied.jsp");
                dispatcher.forward(request, response);
                
            } else {
                
                //Instantiate the Authenticate director and call directUser() method
                authenticatedDirector = new AuthenticatedDirector(request, response, chain);
                authenticatedDirector.directUser();
            }

        } catch (IOException | ServletException ex) {
            System.out.println(ex);
        }

    }

}
