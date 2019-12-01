import DAO.OrderLoadDAO;
import DAO.OrderMakeBean;
import Entities.Order;
import Entities.OrderDish;
import Entities.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ShowOrdersServlet")
public class ShowOrdersServlet extends HttpServlet {
    private ObservableList<OrderMakeBean> orderMakeBeanList = FXCollections.observableArrayList();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        orderMakeBeanList.clear();
        orderMakeBeanList = OrderLoadDAO.loadOrder();

        RequestDispatcher dispatcher = request.getRequestDispatcher("showOrders.jsp");
        request.setAttribute("col", orderMakeBeanList);
        for (OrderMakeBean omb : orderMakeBeanList){
            int table = omb.getTable();
            request.setAttribute("table", table);

            String date = omb.getDate();
            request.setAttribute("date", date);

            double sum = omb.getSum();
            request.setAttribute("sum", sum);

            String staffName = omb.getStaffName();
            request.setAttribute("staffName", staffName);
        }
        dispatcher.forward(request, response);

    }
}
