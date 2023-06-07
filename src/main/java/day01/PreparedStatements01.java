package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatements01
{
    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "dev_user","password");

        //Statement statement= con.createStatement();

        //TASK-1. Using prepared statement, delete students who are from Mathematics department, from students table.
        System.out.println("-------------------TASK-1 --------------------");
        String query1 ="DELETE FROM students WHERE department ILIKE ? ";
        PreparedStatement prs = con.prepareStatement(query1);
        prs.setString(1,"Mathematics");
        prs.setString(1,"Psychology");
        int numDeletedRows =  prs.executeUpdate();

        System.out.println("Deleted ROws :"+numDeletedRows);
        //TASK-2. Insert software engineering department using prepared statement into departments table.
        // (id = 5006, pass_grade=475, campus='South')


        //  statement.close();
        prs.close();
        con.close();





    }
}
