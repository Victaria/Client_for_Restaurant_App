package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection createConnection()
    {
        Connection con = null;
        String url = "jdbc:mysql://206.189.102.66:3306/db"; //MySQL URL and followed by the database name
        String username = "vika"; //MySQL username
        String password = "BaLxECtu2HP6p2nf"; //MySQL password

        try
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance(); //loading mysql driver
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
            System.out.println("Printing connection object " + con);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
}
