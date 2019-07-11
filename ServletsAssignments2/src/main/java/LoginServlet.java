import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private final String username = "arsene";
    private final String password = "@rsene";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("pwd");
        String remember = request.getParameter("remember");

        if(this.username.equals(username) && this.password.equals(password)){
            HttpSession formerSession = request.getSession(false);
            if(formerSession != null){
                formerSession.invalidate();
            }
            HttpSession myNewSession = request.getSession(true);

            myNewSession.setMaxInactiveInterval(4*60);

            Cookie message = new Cookie("message","welcome");
            response.addCookie(message);

            Cookie usernameCookie = new Cookie("username","username");

            if("on".equals(remember)){
                usernameCookie.setMaxAge(60*60*24*30);
                response.addCookie(usernameCookie);
            }else
            {
                usernameCookie.setMaxAge(0);
            }

            response.addCookie(usernameCookie);
            response.sendRedirect("LoginSuccess.jsp");
        } else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginPage.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red> Username and/or password is/are incorrect.</font>");
            rd.include(request,response);
        }

    }

}
