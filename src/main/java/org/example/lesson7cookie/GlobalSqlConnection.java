package org.example.lesson7cookie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GlobalSqlConnection {

    private static Connection conn = null;

    public static Connection get() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/fs1",
                    "postgres",
                    "pg123456"
            );
        }
        return conn;
    }

}


