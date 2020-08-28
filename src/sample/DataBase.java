package sample;

import java.sql.*;

public class DataBase {
    Connection connection;

    public DataBase(Connection connection) {
        this.connection = connection;
    }

    public void createTable()
    {
        String query = "CREATE TABLE IF NOT EXISTS data(id int primary key unique auto_increment,first_name varchar(55))";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

   public void addRecord(String name)
   {
       String query = "INSERT INTO data(first_name)VALUES(?)";
       try {
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1,name);
           if (!preparedStatement.execute())
               System.out.println("data entered");
           else
               System.out.println("data is not entered");
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }

   public void searchRecord(int id)
   {
       String query = "SELECT * FROM data WHERE id=?";
       try {
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1,id+"");

           ResultSet resultSet = preparedStatement.executeQuery();
           resultSet.absolute(1);
           String line = "id is "+id+"\nname is "+resultSet.getString(2)+"\n";
           System.out.println(line);
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }

   public void updateRecord(int id,String first_name)
   {
       String query = "UPDATE data SET first_name=? WHERE id=?";
       try {
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(2,id+"");
           preparedStatement.setString(1,first_name);
           if (!preparedStatement.execute())
               System.out.println("updated");
           else
               System.out.println("not updated");
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }

   public void searchRecordWithName(String name)
   {
       String query = "SELECT * FROM data WHERE first_name=?";

       try {
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1,name);

           ResultSet resultSet = preparedStatement.executeQuery();
           String record = null;
           while (resultSet.next())
           {
               Person person = new Person(resultSet.getInt(1),resultSet.getString(2));
               record = record +"id is "+ person.getId()+"\tuser name is "+person.getName()+"\n";
           }

           System.out.println(record);

       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }


   public void deleteRecord(int id)
   {
       String query = "DELETE FROM data WHERE id=?";
       try {
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1,id+"");
           if (!preparedStatement.execute())
               System.out.println("deleted");
           else System.out.println("not deleted");
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }
}
