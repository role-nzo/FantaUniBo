package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

public class DBController {

    private Connection dbConnection;

    // nome del database
    private String dbHost = "sql.lorenzoziosi.com";
    private String dbName = "lorenzoz62330";
    private String userName = "lorenzoz62330";
    private String password = "@t3riQ0?4I7rei1";

    public final static int MYSQL = 2;

    private Connection openDBConnection() {
        String driver;
        String dbUri;
        driver = "com.mysql.jdbc.Driver";
        dbUri = "jdbc:mysql://sql.lorenzoziosi.com/lorenzoz62330?" + "user=";

        try {
            // System.out.println("DataSource.getConnection() driver = "+driver);
            Class.forName(driver);
            // System.out.println("DataSource.getConnection() dbUri = "+dbUri);
            dbConnection = DriverManager.getConnection(dbUri, userName, password);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found!");
            throw new IllegalArgumentException(e.getMessage());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return dbConnection;
    }

    public Connection getDBConnection(){
        if(dbConnection == null)
            dbConnection = openDBConnection();
        return dbConnection;
    }

    public static void main(String[] args) {
		Connection a = new DBController().getDBConnection();
		System.out.println(a);
	}
}