package bd.edu.seu.ajlab1.repository.DB;

import bd.edu.seu.ajlab1.model.Product;
import bd.edu.seu.ajlab1.repository.ProductDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoDBImplementation implements ProductDao {
    DBConnection db ;
    private Connection connection  = null;
    private ResultSet resultSet;
    private Statement statement;
    private String tabel_name = "tbl_name";

    public ProductDaoDBImplementation() {

         connection = db.getConnection();
    }

    @Override
    public List<Product> readAll() {
        List<Product> productList = new ArrayList<>();
        productList = getProduct().stream().filter(p-> p!=null).collect(Collectors.toList());
        return productList;
    }

    public List<Product> getProduct()
    {
        int productId = 0;
        String productName = "";
        String quantityPerUnit = "";
        String te =" ",te1 = "";
        double unitPrice = 0;
        double unitsInStock = 0;
        double unitsOnOrder = 0;
        double reorderLevel = 0;
        boolean discontinued = false;
        String sql = "select * from "+tabel_name+";";

        List<Product> productList = new ArrayList<>();
        try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                while (resultSet.next())
                {
                    if(productId!=0) {
                        productId = resultSet.getInt("productID");
                        productName = resultSet.getString("productName");
                        quantityPerUnit = resultSet.getString("quantityPerUnit");
                        unitPrice = resultSet.getDouble("unitPrice");
                        unitsInStock = resultSet.getDouble("unitsInStock");
                        unitsOnOrder = resultSet.getDouble("unitsOnOrder");
                        reorderLevel = resultSet.getDouble("reorderLevel");
                        discontinued = resultSet.getString("discontinued").charAt(0)=='1';
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
                    productId=1;

                }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return productList;

    }

    //Insert a Product in database
    @Override
    public void createProduct(Product product) {
        System.out.println("hello");
        String sql = "INSERT INTO "+tabel_name+" VALUES ("+product.getProductID()+","+
                " '"+product.getProductName()+"', 10, 73, '"+product.getQuantityPerUnit()+"',"+product.getUnitPrice()+","
                +product.getUnitsInStock()+","+product.getUnitsOnOrder()+","+product.getReorderLevel()+","+
                product.isDiscontinued()+");";

        try {
            statement = connection.createStatement();
            if(statement.executeUpdate(sql)>0)
            {
                System.out.println("save successful...!");
                System.out.println(product);
            }else
            {
                System.out.println("sorry...!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Delete a product
    @Override
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM "+tabel_name+" WHERE productId = "+productId;
        try {
            statement = connection.createStatement();
            if(statement.executeUpdate(sql)>0)
            {
                System.out.println("Delete successful...!");

            }else
            {
                System.out.println("sorry...!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Update product
    @Override
    public void updateProduct(int productId, Product product) {
        String sql = "UPDATE "+tabel_name+" SET productName = '"+product.getProductName()+"' where productId = "+productId;
        try {
            statement = connection.createStatement();
            if(statement.executeUpdate(sql)>0)
            {
                System.out.println("UPDATE successful...!");

            }else
            {
                System.out.println("sorry...!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product readProduct(int productId) {
        try {

            statement = connection.createStatement();

            String query = "SELECT * FROM tbl_name WHERE productId = " + productId;

            ResultSet resultSet = statement.executeQuery(query);

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
            ex.printStackTrace();
        }

        return null;
    }

}
