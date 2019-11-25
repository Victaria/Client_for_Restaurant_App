import Entities.Staff;
import Parsers.StAXParsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/StaffServlet")
public class StaffServlet extends HttpServlet {
    ObservableList<Staff> staffList = FXCollections.observableArrayList();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        staffList.clear();
        staffList = StAXParsers.staffParse();

        RequestDispatcher dispatcher = request.getRequestDispatcher("staff.jsp");

        request.setAttribute("col", staffList);
        for (Staff staff : staffList){
            int id = staff.getId();
            request.setAttribute("id", id);

            String name = staff.getName();
            request.setAttribute("name", name);
        }
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
