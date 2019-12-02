import DAO.OrderLoadBean;
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
    private ObservableList<OrderLoadBean> orderLoadBeanList = FXCollections.observableArrayList();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        orderLoadBeanList.clear();
        orderLoadBeanList = OrderLoadDAO.loadOrder();

        RequestDispatcher dispatcher = request.getRequestDispatcher("showOrders.jsp");
        request.setAttribute("col", orderLoadBeanList);
        for (OrderLoadBean olb : orderLoadBeanList){

            int orderId = olb.getOrderId();
            request.setAttribute("orderId", orderId);

            int table = olb.getTable();
            request.setAttribute("table", table);

            String date = olb.getDate();
            request.setAttribute("date", date);

            double sum = olb.getSum();
            request.setAttribute("sum", sum);

            String staffName = olb.getStaffName();
            request.setAttribute("staffName", staffName);
        }
        dispatcher.forward(request, response);

    }
}
