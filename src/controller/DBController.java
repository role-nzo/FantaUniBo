package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {

    private Connection dbConnection;

    // tipo di DBMS utilizzato
    private int usedDb = DB2;

    // nome del database
    private String dbName = "dbname";
    private String userName = "dbuser";
    private String password = "dbpasswd";

    public final static int DB2 = 0;
    public final static int HSQLDB = 1;
    public final static int MYSQL = 2;

    private Connection openDBConnection() {
        String driver;
        String dbUri;

        switch (this.usedDb) {
            case DB2:
                driver = "com.ibm.db2.jcc.DB2Driver";
                dbUri = "jdbc:db2://diva.deis.unibo.it:50000/" + dbName;
                break;
            case HSQLDB:
                driver = "org.hsqldb.jdbcDriver";
                // tre modalita' (vedi http://hsqldb.org/doc/guide/ch01.html):
                // 1) hsql --> Hsqldb as a Server
                // 2) mem --> Memory-Only Databases
                // 3) file --> In-Process (Standalone) Mode
                dbUri = "jdbc:hsqldb:hsql://localhost/" + dbName;
                break;
            case MYSQL:
                driver = "com.mysql.jdbc.Driver";
                dbUri = "jdbc:mysql://localhost:3306/" + dbName;
                break;
            default:
                return null;
        }

        try {
            // System.out.println("DataSource.getConnection() driver = "+driver);
            Class.forName(driver);
            // System.out.println("DataSource.getConnection() dbUri = "+dbUri);
            dbConnection = DriverManager.getConnection(dbUri, userName, password);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return dbConnection;
    }

    protected Connection getDBConnection(){
        if(dbConnection == null)
            dbConnection = openDBConnection();
        return dbConnection;
    }

}