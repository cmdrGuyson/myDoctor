package Model;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AuthenticatedDirector implements Director{
    
    private final ServletRequest request;
    private final ServletResponse response;
    private final FilterChain chain;
    
    public AuthenticatedDirector(ServletRequest request, ServletResponse response, FilterChain chain) {
        this.request = request;
        this.response = response;
        this.chain = chain;
    }

    @Override
    public void directUser() {
        try {
            //Direct user to respective page
            chain.doFilter(request, response);
        } catch (IOException | ServletException ex) {
            System.out.println(ex);
        }
    }
    
}
