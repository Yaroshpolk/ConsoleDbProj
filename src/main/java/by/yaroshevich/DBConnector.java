package by.yaroshevich;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private String url = null;
    private String userName = null;
    private String userPassword = null;

    private Connection connection = null;

    public DBConnector(String url, String userName, String userPassword) {
        this.url = url;
        this.userName = userName;
        this.userPassword = userPassword;

        this.run();
    }
    public Connection getConnection() {
        return this.connection;
    }

    public void run() {
        try {
            Class.forName(DRIVER_NAME);
            this.connection = DriverManager.getConnection(this.url, this.userName, this.userPassword);
        } catch (ClassNotFoundException err) {
            System.out.println("Missing JDBC driver...");
            err.printStackTrace();
        } catch (SQLException err) {
            System.out.println("Db connection is failed...");
            err.printStackTrace();
        }
    }
}
