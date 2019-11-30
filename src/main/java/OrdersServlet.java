import Entities.Order;
import Parsers.SAXParsing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {
    private ObservableList<Order> orderList = FXCollections.observableArrayList();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        orderList.clear();
        orderList = SAXParsing.orderParse();

        RequestDispatcher dispatcher = request.getRequestDispatcher("orders.jsp");

        request.setAttribute("col", orderList);

        for (Order order : orderList){
            int id = order.getId();
            request.setAttribute("id", id);

            int table = order.getTable();
            request.setAttribute("table", table);

            double sum = order.getSum();
            request.setAttribute("sum", sum);

            LocalDate date = order.getDate();
            request.setAttribute("date", date);

            String staffName = order.getStaffName();
            request.setAttribute("staffName", staffName);
        }
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
