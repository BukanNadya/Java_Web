package org.example.lesson3;

import java.sql.*;

public class SqlAPP1 {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/fs1",
                "postgres",
                "pg123456"
        );
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM account");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            System.out.printf("id: %d, firstname %s lastname: %s\n", id, firstname, lastname);
        }
        conn.close();
    }
}
