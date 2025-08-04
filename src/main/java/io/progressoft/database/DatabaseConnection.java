package io.progressoft.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.util.Objects.isNull;

public class DatabaseConnection {
    private static Connection INSTANCE;

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver not found", e);
        }
    }

    public static Connection getConnections() throws SQLException {
        if (isNull(INSTANCE)) {
            String url = "jdbc:mysql://localhost:3306/resturant_java_cli";
            String user = "root";
            String password = "Jarah*2002";
            INSTANCE = DriverManager.getConnection(url, user, password);
        }

        return INSTANCE;
    }
}
