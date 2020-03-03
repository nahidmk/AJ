/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.ajlab2.repository;

import bd.edu.seu.ajlab2.exception.ProductAlreadyExistsException;
import bd.edu.seu.ajlab2.model.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author seu
 */
public class ProductDaoMySQLImplementation implements ProductDao {

   // private static  ProductDaoMySQLImplementation productDaoMySQLImplementation = new ProductDaoMySQLImplementation();
    PreparedStatement preparedStatement;
    QuerySingleton querySingleton;
    Statement statement;
    public ProductDaoMySQLImplementation() {
        // TASK: Read the queries from product-sql.properties files
        // You can have a look at the ConnectionSingleton class to see how I did it for the dbconnection.properties
        // We only need to do this once, so implement this one as a Singleton

    }

    // TODO someday we will fix this with Optional
    public Product readProduct(int productId) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            
            // TASK (advanced): use a prepared statement instead of a statement
            //Statement statement = connection.createStatement();


            
            // TASK: replace this query by the one you read from the properties file
//            String query = "SELECT * FROM tbl_name WHERE productId = " + productId;
            String query = querySingleton.getQuery("readProduct");

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                String quantityPerUnit = resultSet.getString("quantityPerUnit");
                double unitPrice = resultSet.getDouble("unitPrice");
                double unitsInStock = resultSet.getDouble("unitsInStock");
                double unitsOnOrder = resultSet.getDouble("unitsOnOrder");
                double reorderLevel = resultSet.getDouble("reorderLevel");
                boolean discontinued = resultSet.getBoolean("discontinued"); 
                
                Product product = new Product(id, 
                        productName, 
                        quantityPerUnit,
                        unitPrice,
                        unitsInStock,
                        unitsOnOrder,
                        reorderLevel,
                        discontinued);
                
                return product;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @Override
    public List<Product> readAll() {
        List<Product> productList = new ArrayList<>();
        
        try {
            Connection connection = ConnectionSingleton.getConnection();
            
            // TASK (advanced): use a prepared statement instead of a statement
//            Statement statement = connection.createStatement();
            String query = querySingleton.getQuery("read_all");
            preparedStatement = connection.prepareStatement(query);
            // TASK: replace this query by the one you read from the properties file
//            String query = "SELECT * FROM product";
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                String quantityPerUnit = resultSet.getString("quantityPerUnit");
                double unitPrice = resultSet.getDouble("unitPrice");
                double unitsInStock = resultSet.getDouble("unitsInStock");
                double unitsOnOrder = resultSet.getDouble("unitsOnOrder");
                double reorderLevel = resultSet.getDouble("reorderLevel");
                boolean discontinued = resultSet.getBoolean("discontinued"); 
                
                Product product = new Product(productId, 
                        productName, 
                        quantityPerUnit,
                        unitPrice,
                        unitsInStock,
                        unitsOnOrder,
                        reorderLevel,
                        discontinued);
                
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList;
    }

    @Override
    public Product createProduct(Product product) throws ProductAlreadyExistsException {
        Product product1 = null;
        try {
            Connection connection = ConnectionSingleton.getConnection();

            // TASK (advanced): use a prepared statement instead of a statement
           // Statement statement = connection.createStatement();

            String query = querySingleton.getQuery("creatProduct");
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,product.getProductID());
            preparedStatement.setString(2,product.getProductName());
            preparedStatement.setString(3,"10");
            preparedStatement.setString(4,"8");
            preparedStatement.setString(5,product.getQuantityPerUnit());
            preparedStatement.setDouble(6,product.getUnitPrice());
            preparedStatement.setDouble(7,product.getUnitsInStock());
            preparedStatement.setDouble(8,product.getUnitsOnOrder());
            preparedStatement.setDouble(9,product.getReorderLevel());
            preparedStatement.setBoolean(10,product.isDiscontinued());
            int rowCount = preparedStatement.executeUpdate();
             product1 = readProduct(product.getProductID());
            // TASK: replace this query by the one you read from the properties file
            System.out.println("Inserted " + rowCount + " rows");

            // TODO how can we tell the user that the insert operation failed?
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new ProductAlreadyExistsException(product);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ProductDaoMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TASK: instead of returning null, return the Product that got inserted into the DB
        return product1;
    }

    @Override
    public List<Product> deleteAll() {
        List<Product>productList = readAll();;
        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = querySingleton.getQuery("delete_all");
            preparedStatement = connection.prepareStatement(query);
            int rowCount = preparedStatement.executeUpdate();
            System.out.println("Deleted " + rowCount + " rows");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ProductDaoMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // TASK: instead of returning null, return all the products that got deleted
        return productList;
    }

    @Override
    public List<Product> createProducts(List<Product> productList) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = querySingleton.getQuery("creatProduct");
        try {
            preparedStatement = connection.prepareStatement(query);
            productList.stream()
                    .forEach(product -> {
                        try {
                            preparedStatement.setInt(1, product.getProductID());
                            preparedStatement.setString(2, product.getProductName());
                            preparedStatement.setString(3, "10");
                            preparedStatement.setString(4, "8");
                            preparedStatement.setString(5, product.getQuantityPerUnit());
                            preparedStatement.setDouble(6, product.getUnitPrice());
                            preparedStatement.setDouble(7, product.getUnitsInStock());
                            preparedStatement.setDouble(8, product.getUnitsOnOrder());
                            preparedStatement.setDouble(9, product.getReorderLevel());
                            preparedStatement.setBoolean(10, product.isDiscontinued());

                            preparedStatement.executeUpdate();
                        }catch (SQLException e)
                        {
                            e.printStackTrace();
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Product> productList1 = readAll();
        return productList;
    }

    @Override
    public Product deleteProduct(int productId) {
        Connection connection = ConnectionSingleton.getConnection();
        Product product = readProduct(productId);
        String query = querySingleton.getQuery("deleteProduct");
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,productId);
            int rowcount = preparedStatement.executeUpdate();
            System.out.println("rowcount : "+rowcount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        Connection connection = ConnectionSingleton.getConnection();
        Product product1 = null;
        String query = querySingleton.getQuery("updateProduct");
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product.getProductID());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, "10");
            preparedStatement.setString(4, "8");
            preparedStatement.setString(5, product.getQuantityPerUnit());
            preparedStatement.setDouble(6, product.getUnitPrice());
            preparedStatement.setDouble(7, product.getUnitsInStock());
            preparedStatement.setDouble(8, product.getUnitsOnOrder());
            preparedStatement.setDouble(9, product.getReorderLevel());
            preparedStatement.setBoolean(10, product.isDiscontinued());
            preparedStatement.setInt(11,productId);

            int rowCount = preparedStatement.executeUpdate();
            System.out.println("rowCount : "+rowCount);
            product1 = readProduct(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product1;
    }

    public int write_and_read_from_CSV_to_Mysql(StringBuilder stringBuilder)
    {
        long start = System.currentTimeMillis();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("products1.csv"));
            bw.write(stringBuilder.toString());
            bw.close();
            System.out.println("Csv write Complete...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = ConnectionSingleton.getConnection();

        //String query = querySingleton.getQuery("creatProduct");
        final int rowNumber[] = {0};
        try {
           // preparedStatement = connection.prepareStatement(query);
            statement = connection.createStatement();
            Files.lines(Paths.get("products1.csv"))
                    .map(this::getProduct)
                    .filter(product -> product != null)

                    .forEach(product -> {
                        try {
                            String sql = "INSERT INTO tbl_name VALUES ("+product.getProductID()+","+
                                    " '"+product.getProductName()+"', 10, 73, '"+product.getQuantityPerUnit()+"',"+product.getUnitPrice()+","
                                    +product.getUnitsInStock()+","+product.getUnitsOnOrder()+","+product.getReorderLevel()+","+
                                    product.isDiscontinued()+");";
                            rowNumber[0] = statement.executeUpdate(sql);
//                            preparedStatement.setInt(1, product.getProductID());
//                            preparedStatement.setString(2, product.getProductName());
//                            preparedStatement.setString(3, "10");
//                            preparedStatement.setString(4, "8");
//                            preparedStatement.setString(5, product.getQuantityPerUnit());
//                            preparedStatement.setDouble(6, product.getUnitPrice());
//                            preparedStatement.setDouble(7, product.getUnitsInStock());
//                            preparedStatement.setDouble(8, product.getUnitsOnOrder());
//                            preparedStatement.setDouble(9, product.getReorderLevel());
//                            preparedStatement.setBoolean(10, product.isDiscontinued());
//
//                            preparedStatement.executeUpdate();

                        }catch (SQLException e)
                        {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException | SQLException ex) {
            Logger.getLogger(ProductDaoCsvImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        long end = System.currentTimeMillis();
        return rowNumber[0];
    }
    private Product getProduct(String line) {
        if (line.startsWith("p"))
            return null;

        String[] tokens = line.split("\\,");

        int productId = Integer.parseInt(tokens[0]);
        String productName = tokens[1];
        String quantityPerUnit = tokens[4];
        double unitPrice = Double.parseDouble(tokens[5]);
        double unitsInStock = Double.parseDouble(tokens[6]);
        double unitsOnOrder = Double.parseDouble(tokens[7]);
        double reorderLevel = Double.parseDouble(tokens[8]);
        boolean discontinued = tokens[9].charAt(0) == '1';

        Product product = new Product(productId,
                productName,
                quantityPerUnit,
                unitPrice,
                unitsInStock,
                unitsOnOrder,
                reorderLevel,
                discontinued);
        return product;
    }
}











