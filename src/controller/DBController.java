package controller;

import java.sql.Connection;

public abstract class DBController{

    private Connection dbConnection;
    
    private Connection openDBConnection(){
        return null;
        //TODO
    }

    public abstract Connection getDBConnection();

}