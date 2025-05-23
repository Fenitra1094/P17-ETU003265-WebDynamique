package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String URL = "jdbc:mysql://172.80.237.53:3306/db_s2_ETU003265";
    private static final String USER = "ETU003265";
    private static final String PASSWORD = "iNl2w9EQ";

    public Connection getConnexion() throws SQLException {
        Connection connection = null;
        try {
            // Charger explicitement le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return connection;
    }
}