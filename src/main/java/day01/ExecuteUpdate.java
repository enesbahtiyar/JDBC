package day01;

import java.sql.*;

public class ExecuteUpdate
{
    public static void main(String[] args) throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "enes", "240921");

        Statement statement = connection.createStatement();

        //TASK-1. Update salaries of developers whose salaries are less than average salary with average salary

        System.out.println("---------------Task -1 ------------ ");

        String query1= "UPDATE developers  SET salary = (SELECT AVG(salary) FROM developers)" +
                "WHERE salary <(SELECT AVG(salary) FROM developers )";

        int updateRows= statement.executeUpdate(query1);
        System.out.println("updateRows :"+updateRows);

        String query2="SELECT name , salary FROM developers";
        ResultSet resultSet= statement.executeQuery(query2);
        while (resultSet.next())
        {
            System.out.println(resultSet.getString("name")+"--"+resultSet.getDouble("salary"));
        }

        //task-2 Add new developer to developers table
        System.out.println("---------------Task-2 ------------ ");

        //String query3 = "INSERT INTO developers (id,name,salary,prog_lang) VALUES(21, 'EMIR',100, 'python')";
        //int numInsert = statement.executeUpdate(query3);
        //System.out.println("Inserted data: " + numInsert);

        //TASK-3. DELETE row which has id of 14

        System.out.println("---------Task 3 ------------");

        int deleteRows = statement.executeUpdate("DELETE FROM developers WHERE id =14");
        System.out.println("Deleted Rows :" +deleteRows);

        //TASK-4. DELETE rows from developers table if  prog_lang is "Ruby"
        //ILIKE is  not case-sensitive
        System.out.println("---------Task 4 ------------");
        int deleteRows2 = statement.executeUpdate("DELETE FROM developers WHERE prog_lang ILIKE 'Ruby'");
    }
}
