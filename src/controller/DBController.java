package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {

    private Connection dbConnection;

    // nome del database
    private String dbHost = "remotemysql.com:3306";
    private String dbName = "FBgfoLGUa1";
    private String userName = "FBgfoLGUa1";
    private String password = "rrFgPoL6S1";

    public final static int MYSQL = 2;

    private Connection openDBConnection() {
        String driver;
        String dbUri;
        driver = "com.mysql.jdbc.Driver";
        dbUri = "jdbc:mysql://" + dbHost + "/" + dbName;

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
}