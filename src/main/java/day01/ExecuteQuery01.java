package day01;

import java.sql.*;

public class ExecuteQuery01
{
    public static void main(String[] args) throws SQLException
    {
        //create connection
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "enes", "240921");

        //create statements
        Statement statement = connection.createStatement();

        //TASK-1. GET/SELECT  "country_name" from countries table with ID between 5 and 10
        System.out.println("----------Task1---------");
        String query = "SELECT country_name FROM countries WHERE id BETWEEN 5 AND 10";
        boolean sql = statement.execute(query);
        System.out.println(sql);

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next())
        {
            System.out.println(resultSet.getString("country_name"));
        }

        //TASK - 2: Get "phone_code" and "country_name" from countries table,
        // whose phone code is greater than 200
        System.out.println("----------Task2---------");
        String query1 = "SELECT phone_code, country_name FROM countries WHERE phone_code > 200";
        ResultSet resultSet1 = statement.executeQuery(query1);

        while (resultSet1.next())
        {
            System.out.print(resultSet1.getInt("phone_code") + " ");
            System.out.print(resultSet1.getString("country_name") + "\n");
        }



        //TASK-3. Get all information about the developers whose salary is lowest
        System.out.println("----------Task3---------");
        String query2 = "SELECT * FROM developers WHERE salary = (SELECT min(salary) FROM developers)";
        ResultSet resultSet2 = statement.executeQuery(query2);
        while (resultSet2.next())
        {
            System.out.println(resultSet2.getInt("id") + "---" + resultSet2.getString("name") + "---" + resultSet2.getString("prog_lang") + "---" + resultSet2.getDouble("salary"));
        }

        //TASK - 4 : Display students' name and grade whose grades are higher than average passing grade of departments.
        System.out.println("----------Task4---------");
        String query3 = "SELECT name, grade FROM students WHERE grade > (SELECT AVG(pass_grade) FROM departments)";
        ResultSet resultSet3 = statement.executeQuery(query3);
        while (resultSet3.next())
        {
            System.out.println(resultSet3.getString("name") + "---" + resultSet3.getInt("grade"));
        }

        statement.close();
        connection.close();
    }
}
