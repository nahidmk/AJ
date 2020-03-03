package bd.edu.seu.ajlab2.repository;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuerySingleton {

    private static QuerySingleton querySingleton = new QuerySingleton();
    private static Properties properties;

    private static void readQueryParameters(){
        String productQueryFilename = "product-sql.properties";

        try (InputStream inputStream = ConnectionSingleton.class.getClassLoader().getResourceAsStream(productQueryFilename)) {
                properties = new Properties();

            if (properties != null && inputStream != null) {
                System.out.println("Reading from " + productQueryFilename);
                properties.load(inputStream);
            } else {
                System.err.println("Properties/InputStream object is null");
            }

        } catch (IOException ex) {
            Logger.getLogger(ConnectionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getQuery(String queryName)
    {
        return properties.getProperty(queryName);
    }

    private QuerySingleton() {
        readQueryParameters();
    }
}
