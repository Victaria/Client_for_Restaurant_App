package Login;

import DAO.RegisterBean;
import DAO.RegisterDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
       /* RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);*/
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //request.getRequestDispatcher("index.jsp").include(request, response);
        RegisterBean registerBean = new RegisterBean();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password1");
        String repeatPassword = request.getParameter("password2");
        out.println(password);
        registerBean.setEmail(email);
        registerBean.setPassword(password);
        registerBean.setUserName(name);

        RegisterDao registerDao = new RegisterDao();
        String userRegistered = registerDao.registerUser(registerBean);

        if(userRegistered.equals("SUCCESS"))   //On success, you can display a message to user on Home page
        {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else   //On Failure, display a meaningful message to the User.
        {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }


       /* if(!password.isEmpty()){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("index.jsp");
            out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
           // response.sendRedirect("index.jsp");
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
            out.println("<font color=red>Something went wrong. Please, try again.</font>");
            rd.include(request, response);
        }
        out.close();*/
    }

}
