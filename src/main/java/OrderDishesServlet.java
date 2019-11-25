import Entities.OrderDish;
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

@WebServlet("/OrderDishesServlet")
public class OrderDishesServlet extends HttpServlet {
    private ObservableList<OrderDish> odList = FXCollections.observableArrayList();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        odList.clear();
        odList = SAXParsing.orderDishParse();

        RequestDispatcher dispatcher = request.getRequestDispatcher("orderDishes.jsp");

        request.setAttribute("col", odList);

        for (OrderDish od : odList){
            int id = od.getId();
            request.setAttribute("id",id);

            int amount = od.getAmount();
            request.setAttribute("amount", amount);

            String odDishName = od.getDishName();
            request.setAttribute("dishName", odDishName);

            double odOrderId = od.getOrderId();
            request.setAttribute("orderId", odOrderId);
        }
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
