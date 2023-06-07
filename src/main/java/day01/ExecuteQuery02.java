package day01;

import java.sql.*;

public class ExecuteQuery02
{
    public static void main(String[] args) throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "enes", "240921");

        Statement statement = connection.createStatement();

        //Task-1: Print department name and grade of department which has second highest pass_grade
        System.out.println("------Task1-------");
        String query1 = "SELECT department, pass_grade FROM departments ORDER BY pass_grade DESC OFFSET 1 LIMIT 1";
        ResultSet resultSet1 = statement.executeQuery(query1);
        resultSet1.next();
        System.out.println(resultSet1.getString("department") +"---"+ resultSet1.getInt("pass_Grade"));

        //was asked during interview
        //Task-2: Print department name and pass_grade of department which has second highest pass_grade using sub-query
        System.out.println("------Task2-------");
        String query2 = "SELECT department, pass_grade FROM departments WHERE pass_grade = (SELECT max(pass_grade)" + "FROM departments WHERE pass_grade < (SELECT max(pass_grade) FROM departments))";
        ResultSet resultSet2 = statement.executeQuery(query2);
        resultSet2.next();
        System.out.println(resultSet2.getString("department") +"---"+ resultSet2.getInt("pass_Grade"));

        //Task-3: List department name, campus and highest grades of students from every department
        System.out.println("------Task3-------");
        String query3 = "SELECT department, campus, (SELECT max(grade) FROM students s WHERE d.department = s.department) AS max_grade FROM departments d";
        ResultSet resultSet3 = statement.executeQuery(query3);
        while (resultSet3.next())
        {
            System.out.println(resultSet3.getString("department") + "---" + resultSet3.getString("campus") + "---" + resultSet3.getInt("max_grade"));
        }

        statement.close();
        connection.close();
    }
}
