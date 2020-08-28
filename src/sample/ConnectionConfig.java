package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionConfig {





    public static Connection getConnection()
    {
        Connection connection = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","");
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        if (connection == null)
            System.out.println("connection not occur");
        else
            System.out.println("connection occur");
        return connection;
    }
}
