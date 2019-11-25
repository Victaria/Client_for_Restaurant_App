import Entities.Recipe;
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
import java.time.LocalDate;

@WebServlet("/RecipeServlet")
public class RecipeServlet extends HttpServlet {
    ObservableList<Recipe> recipeList = FXCollections.observableArrayList();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        recipeList.clear();
        recipeList = StAXParsers.receiptsParse();

        RequestDispatcher dispatcher = request.getRequestDispatcher("receipts.jsp");

        request.setAttribute("col", recipeList);
        for (Recipe recipe : recipeList){
            int id = recipe.getId();
            request.setAttribute("id", id);

            String dishName = recipe.getDishName();
            request.setAttribute("dishName", dishName);

            String prodName = recipe.getProductName();
            request.setAttribute("productName", prodName);

            int amount = recipe.getAmount();
            request.setAttribute("amount", amount);
        }
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
