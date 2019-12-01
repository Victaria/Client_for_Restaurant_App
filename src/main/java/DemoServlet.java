import Entities.Products;
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

@WebServlet("/DemoServlet")

public class DemoServlet extends HttpServlet {
    private DOMParser parser = new DOMParser();
    private ObservableList<Products> productsList = FXCollections.observableArrayList();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productsList.clear();
        productsList = parser.loadProductXMLFile();
        RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");

        request.setAttribute("col", productsList);

        for (Products pr : productsList) {

            int id = pr.getId();
            request.setAttribute("id",id);

            String prodName = pr.getName();
            request.setAttribute("name", prodName);

            double prodPrice = pr.getPrice();
            request.setAttribute("price",prodPrice);

            double prodAmount = pr.getAmount();
            request.setAttribute("amount",prodAmount);

        }
            dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
