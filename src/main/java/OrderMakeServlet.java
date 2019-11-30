import DAO.LoginBean;
import DAO.OrderMakeBean;
import DAO.OrderMakeDao;
import Entities.Dishes;
import Entities.Order;
import Entities.OrderDish;
import Parsers.DOMParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/OrderMakeServlet")
public class OrderMakeServlet extends HttpServlet {
    private DOMParser parser = new DOMParser();
    private ObservableList<Dishes> dishesList = FXCollections.observableArrayList();
    private ObservableList<OrderDish> odList = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dishesList.clear();
        odList.clear();
        orderList.clear();
        dishesList = parser.loadDishesXMLFile();

        RequestDispatcher dispatcher = request.getRequestDispatcher("addOrder.jsp");

        request.setAttribute("col", dishesList);
        for (Dishes ds : dishesList) {
            int id = ds.getId();
            request.setAttribute("id",id);

            String name = ds.getName();
            request.setAttribute("name", name);
        }
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        OrderMakeBean orderMakeBean = new OrderMakeBean();

        int table = Integer.parseInt(request.getParameter("table"));
        int counter = Integer.parseInt(request.getParameter("dishesCount"));
        LocalDate date = LocalDate.now();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            ArrayList<String> name = new ArrayList<>();
            ArrayList<Integer> amount = new ArrayList<>();
            for (int i = 1; i <= counter; i++){
              //  name.add(request.getParameter("dishFor" + i));
                for (Dishes dish : dishesList){
                    int equ = dish.compare(dish.getId(), Integer.parseInt(request.getParameter("dishFor" + i)));
                    if (equ == 0) name.add(dish.getName());
                }
                amount.add(Integer.parseInt(request.getParameter("countFor" + i)));
            }
            orderMakeBean.setTable(table);
            orderMakeBean.setAmount(amount);
            orderMakeBean.setDishName(name);
            orderMakeBean.setDate(date);
            orderMakeBean.setSum(0);

        //find user Id and Order Id

        orderMakeBean.setUserId(LoginBean.getStaticId());
       // out.println(LoginBean.getStaticId());
        OrderMakeDao omd = new OrderMakeDao();
        String loadOrder = omd.createOrder(orderMakeBean);

        if (loadOrder.equals("SUCCESS")){
            out.println("success");
        } else out.println("noooo");


        //out.println("Your order is preparing.");
       // request.getRequestDispatcher("addOrder.jsp").forward(request, response);
    }
}
