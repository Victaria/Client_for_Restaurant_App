package Login;

import DAO.LoginBean;
import DAO.LoginDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LogiServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
       /* RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);*/
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LoginBean loginBean = new LoginBean(); //creating object for LoginBean class, which is a normal java class, contains just setters and getters. Bean classes are efficiently used in java to access user information wherever required in the application.

        loginBean.setEmail(email); //setting the username and password through the loginBean object then only you can get it in future.
        loginBean.setPassword(password);

        LoginDAO loginDao = new LoginDAO(); //creating object for LoginDao. This class contains main logic of the application.

        String userValidate = loginDao.authenticateUser(loginBean);


        if(userValidate.equals("SUCCESS")) //If function returns success string then user will be rooted to Home page
        {
            HttpSession session = request.getSession(true);
            session.setAttribute("email", email);
            Cookie loginCookie = new Cookie("email", email);
            loginCookie.setMaxAge(30*60);
            response.addCookie(loginCookie);

            request.setAttribute("email", email); //with setAttribute() you can define a "key" and value pair so that you can get it in future using getAttribute("key")
            request.getRequestDispatcher("/startPage.jsp").forward(request, response);
        } else
        {
            request.setAttribute("errMessage", userValidate); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
            request.getRequestDispatcher("/index.jsp").forward(request, response);//forwarding the request
        }

      /*  if(password.equals("ad") && email.equals("ad")){
            HttpSession session = request.getSession(true); // reuse existing
            // session if exist
            // or create one
            session.setAttribute("email", email);
            Cookie loginCookie = new Cookie("email", email);
            loginCookie.setMaxAge(30*60);
            response.addCookie(loginCookie);
            response.sendRedirect("startPage.jsp");
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("index.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }*/
    }

    }
