import DAO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.plugin.dom.DOMObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/InfoServlet")
public class InfoServlet extends HttpServlet {
    private ObservableList<OrderLoadBean> orderLoadBeanList = FXCollections.observableArrayList();
    private ObservableList<OrderDetailsBean> odbList = FXCollections.observableArrayList();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id =  request.getParameter("id");
        request.setAttribute("id", id);
        PrintWriter out = response.getWriter();

        orderLoadBeanList.clear();
       // odbList.clear();
        orderLoadBeanList = OrderLoadDAO.loadOrder();

        RequestDispatcher dispatcher = request.getRequestDispatcher("info.jsp");

        for (OrderLoadBean olb : orderLoadBeanList){
            if (olb.getOrderId() == Integer.parseInt(id)){

                int table = olb.getTable();
                request.setAttribute("table", table);

                String staffName = olb.getStaffName();
                request.setAttribute("staffName", staffName);

                String date = olb.getDate();
                request.setAttribute("date", date);

                request.setAttribute("sum",olb.getSum());

                odbList = olb.getOrderDetails();
                request.setAttribute("col", odbList);

                for (OrderDetailsBean odb : odbList){
                    String dishName = odb.getDishName();
                    request.setAttribute("dishName", dishName);

                    int amount = odb.getAmount();
                    request.setAttribute("amount", amount);

                    Double price = odb.getDishPrice();
                    request.setAttribute("dishPrice", price*amount);
                }


              /*  for (int i = 0; i < omb.getDishName().size(); i++){
                    OrderDetailsBean odb = new OrderDetailsBean();
                    odb.setAmount(omb.getAmount().get(i));
                    odb.setDishName(omb.getDishName().get(i));
                    odb.setDishPrice(omb.getDishPrice().get(i));
                    odbList.add(odb);
                }

                request.setAttribute("col", odbList);

                for (OrderDetailsBean odb : odbList){
                    int amount = odb.getAmount();
                    request.setAttribute("amount", amount);

                    String dishName = odb.getDishName();
                    request.setAttribute("dishName", dishName);

                    Double price = odb.getDishPrice();
                    request.setAttribute("dishPrice", price*amount);
                }*/
            }
        }

        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
