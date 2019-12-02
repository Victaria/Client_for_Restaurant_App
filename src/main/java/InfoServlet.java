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
    private ObservableList<OrderDetailsBean> odb2List = FXCollections.observableArrayList();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id =  request.getParameter("id");
        request.setAttribute("id", id);

        //PrintWriter out = response.getWriter();
        odb2List.clear();
        orderLoadBeanList.clear();
        odbList.clear();

        orderLoadBeanList = OrderLoadBean.getOlb();
        odbList = OrderDetailsBean.getOdb();

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
                }
        }

        for (OrderDetailsBean odb : odbList) {
            if (odb.getId() == Integer.parseInt(id)) {

                String dishName = odb.getDishName();
                request.setAttribute("dishName", dishName);

                int amount = odb.getAmount();
                request.setAttribute("amount", amount);

                Double price = odb.getDishPrice();
                request.setAttribute("dishPrice", price * amount);

                odb2List.add(odb);
            }
        }
        request.setAttribute("col", odb2List);

         dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
