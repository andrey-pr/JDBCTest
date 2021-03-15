package org.dev.scud;

import java.sql.*;

public class Test {
    private static Connection conn;
    private static Statement statement;
    private static final String url = "jdbc:mysql://localhost/test";

    private static void connect() throws SQLException {
        conn = DriverManager.getConnection(url, "root", "");
    }

    private static ResultSet executeSQL(String sql) throws SQLException {
        statement = conn.createStatement();
        return statement.executeQuery(sql);
    }

    private static void closeStatement() throws SQLException {
        statement.close();
    }

    private static void disconnect() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static void main(String[] args) {
        try {
            connect();
            //prepareDatabase();
            ResultSet rs = executeSQL("SELECT * FROM users;");
            while (rs.next()) {
                System.out.println("\n================\n");
                System.out.println("id: " + rs.getInt("id"));
                System.out.println("id: " + rs.getString("name"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                closeStatement();
                disconnect();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
