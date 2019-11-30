package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {
    public String authenticateUser(LoginBean loginBean){
        String userMail = loginBean.getEmail(); //Keeping user entered values in temporary variables.
        String password = loginBean.getPassword();

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String userMailDB = "";
        String passwordDB = "";
        String idDB = "";

        try {
            con = DBConnection.createConnection(); //establishing connection
            statement = con.createStatement(); //Statement is used to write queries. Read more about it.
            resultSet = statement.executeQuery("select id, email, name, password from users");
            while (resultSet.next()) // Until next row is present otherwise it return false
            {
                userMailDB = resultSet.getString("email"); //fetch the values present in database
                passwordDB = resultSet.getString("password");


                if (userMail.equals(userMailDB) && password.equals(passwordDB)) {
                    loginBean.setId(Integer.parseInt(resultSet.getString("id")));
                    loginBean.setUserName(resultSet.getString("name"));
                    return "SUCCESS"; ////If the user entered values are already present in database, which means user has already registered so I will return SUCCESS message.
                }
            }
        } catch(SQLException e)
            {
                e.printStackTrace();
            }
            return "Invalid user credentials";
    }

}
