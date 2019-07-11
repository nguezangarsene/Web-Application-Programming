import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloServlet")
public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String supportEmail = getServletContext().getInitParameter("Support-email");
        int ticket = request.hashCode();
        out.print("<html><head><title>Test</title></head><body>");
        out.print("<p>Thank you! "+name+" for contacting us. We should receive reply from us with in 24 hrs in your email address "+
                email+" Let us know in our support email "+supportEmail+" if you don’t receive reply within 24 hrs. Please be sure to attach your reference " +
                ticket+" in your email. </p>");
        out.print("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Test</title></head><body>");
        out.print("<form method='post'>");
        out.print("<p>Please click the button</p>");
        out.print("<ul>");
        out.println("<li>Name:<input type='text' name='name'/><li>");
        out.println("<li>Email:<input type='text' name='email'/><li>");
        out.println("<li>Problem:<input type='text' name='problem'/><li>");
        out.println("<li>Problem Description:<textarea name=''></textarea><li>");
        out.println("</ul>");
        out.print("<input type='submit' value='Help'/>");
        out.print("</form>");
        out.print("</body></html>");
    }
}
