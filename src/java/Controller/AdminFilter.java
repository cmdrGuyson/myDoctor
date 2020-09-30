package Controller;

import Model.Director;
import Model.ProxyDirector;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/* Prevent users from accessing servlets that should only be accessed by admins */
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        //The ProxyDirector will instantiate AuthenticatedDirector object and call directUser() if logged in user is an admin
        Director director = new ProxyDirector(request, response, chain);
        director.directUser();
        
    }

}
