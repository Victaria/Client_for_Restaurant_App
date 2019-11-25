import Entities.Dishes;
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

@WebServlet("/DishesServlet")
public class DishesServlet extends HttpServlet {
    private DOMParser parser = new DOMParser();
    private ObservableList<Dishes> dishesList = FXCollections.observableArrayList();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dishesList.clear();
        dishesList = parser.loadDishesXMLFile();
        RequestDispatcher dispatcher = request.getRequestDispatcher("dishes.jsp");

        request.setAttribute("col", dishesList);
        request.setAttribute("size", dishesList.size()-1);
        for (Dishes ds : dishesList) {

            int id = ds.getId();
            request.setAttribute("id",id);

            String name = ds.getName();
            request.setAttribute("name", name);

            double price = ds.getPrice();
            request.setAttribute("price", price);

            double weight = ds.getWeight();
            request.setAttribute("weight", weight);

            double sum = ds.getSum();
            request.setAttribute("sum", sum);

        }
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
