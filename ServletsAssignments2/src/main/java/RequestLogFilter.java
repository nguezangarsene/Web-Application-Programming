import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@WebFilter(filterName = "RequestLogFilter")
public class RequestLogFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Instant startTime = Instant.now();
        try {
            chain.doFilter(req, resp);
        }finally {
            Instant endTime = Instant.now();
            Duration timePassed = Duration.between(startTime,endTime);
            HttpServletRequest in = (HttpServletRequest) req;
            HttpServletResponse out = (HttpServletResponse) resp;

            String length = out.getHeader("content-length");
            if(length == null || length.length() == 0)
                length = "-";
            System.out.println(in.getRemoteAddr()+" - - ["+ startTime+"]"+"\""+in.getMethod()+" "
            +in.getRequestURI() +" "+in.getProtocol()+"\""+out.getStatus()+" "+length+" "
            +timePassed.toNanos()+" nano-seconds");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
