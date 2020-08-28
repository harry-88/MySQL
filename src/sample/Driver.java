package sample;

import java.sql.Connection;

public class Driver {
    public static void main(String[] args) {

        Connection connection = new ConnectionConfig().getConnection();
        DataBase dataBase = new DataBase(connection);
        if (connection != null)
        {
            dataBase.createTable();
            dataBase.deleteRecord(5);
            dataBase.deleteRecord(6);
            dataBase.deleteRecord(7);
        }


    }
}
