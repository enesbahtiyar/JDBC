package day01;

import java.sql.*;

public class PreparedStatements
{
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "enes", "240921");

        Statement statement = connection.createStatement();

        //TASK-1 Update pass_Grade to 475 of Mathematichs department (use preparedstatement)

        String query1 = "UPDATE departments SET pas_grade =? WHERE department ILIKE ?";
        PreparedStatement prs = connection.prepareStatement(query1);
        prs.setInt(1, 475);
        prs.setString(2, "mathematics");
        int numOfUpdateRows = prs.executeUpdate();
        System.out.println("numOfUpdateRows "+numOfUpdateRows);

        //TASK-2. Update pass_grade to 455 of Literature department (use PreparedStatement)
        prs.setInt(1, 455);
        prs.setString(2, "literature");
        int numOfUpdateRows2 = prs.executeUpdate();
        System.out.println("numOfUpdateRows "+numOfUpdateRows2);

    }
}
