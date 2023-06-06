package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.step: register driver
        Class.forName("org.postgresql.Driver");

        //2.step: create a connection to get database
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "enes", "240921");

        //3.step: Create Statements to execute SQL Query
        Statement statement = connection.createStatement();

        if(connection != null)
        {
            System.out.println("connected with success");
        }
        else
        {
            System.out.println("connection is failed");
        }
        //4.step: Execute SQL query
        //TASK: create a table named "employee" with column names of :
        // "employee_id", "employee_name", "salary"
        //CREATE TABLE employee(employee_id INT, employee_name VARCHAR(50), salary REAL);

        boolean sql = statement.execute("CREATE TABLE employee(employee_id INT, employee_name VARCHAR(50), salary REAL);");
        System.out.println(sql);

        /*
          //execute () returns boolean value and can be used for DDL data definition language or Data Query Language
          execute() method can be used in DDL(Data Definition Language --> Crate Table,
                Drop Table, Alter Table) and DQL (Data Query Language --> Select)
            1) If you use execute() method with DDL everytime you will get false.
            2) If you use execute() method with DQL  you will get false or true.
            If you get the resultSet object as return you will get true otherwise you will get false.
         */


        //TASK 2: add Varchar (20) column name "city" to employee table

        String query = "ALTER TABLE employee ADD COLUMN city VARCHAR(20)";
        boolean sql2 = statement.execute(query);
        System.out.println(sql2);

        //Task 3: delete employee table from schema

        boolean sql3 = statement.execute("DROP TABLE employee");
        System.out.println(sql3);

        //step 5
        statement.close();
        connection.close();
    }
}
