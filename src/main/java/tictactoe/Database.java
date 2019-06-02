package tictactoe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String url;
    private final String user;
    private final String password;

    public Database() {
        this.url = System.getenv("DBURL");
        this.user = System.getenv("DBUSERNAME");
        this.password = System.getenv("DBPASSWORD");
    }

    public Connection connect() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
            System.out.println(e.getMessage());
        }

        return conn;
    }
}