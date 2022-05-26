package db;

import java.sql.Connection;
import controller.DBController;
import org.gjt.mm.mysql.Driver;

public class TestDB {
    public static void main(String[] args) throws Exception {
        Driver d = new Driver();
        System.out.println(d);
    }
}
