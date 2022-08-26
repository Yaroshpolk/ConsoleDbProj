package by.yaroshevich;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private final String url;
    private final String userName;
    private final String userPassword;
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
            System.out.println("Connection completed \n\n");
        } catch (ClassNotFoundException err) {
            Messenger.printErrorMessage("Missing JDBC driver... \n\n");
            err.printStackTrace();
        } catch (SQLException err) {
            Messenger.printErrorMessage("Db connection is failed...");
            if (err.getErrorCode() == 1045) {
                System.out.println("Access denied.");
            }
        }
    }
}
