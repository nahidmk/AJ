package bd.edu.seu.ajlab1.repository.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private  static final String HOSTNAME = "localhost";
    private  static final String DBNAME = "csv_db";
    private static final String PASSWORD = "manik";
    private static final String USERNAME = "root";
    private static final String URL = "jdbc:mysql://"+HOSTNAME+"/"+DBNAME;
    private Connection connection = null;

    public DBConnection() {
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("DAO : Data base is connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection()
    {
        return connection;
    }
}
