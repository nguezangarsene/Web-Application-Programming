import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    private ServletContext context;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        chain.doFilter(req, resp);

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);

        if(session == null){
            this.context.log("Unauthorized access request");
            response.sendRedirect(request.getContextPath()+"/loginPage.html");
        }else
        {
            chain.doFilter(req,resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {
       this.context = config.getServletContext();
       this.context.log("Authentication Initialized");
    }

}
