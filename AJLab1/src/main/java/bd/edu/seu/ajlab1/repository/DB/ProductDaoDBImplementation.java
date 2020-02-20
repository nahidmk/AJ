package bd.edu.seu.ajlab1.repository.DB;

import bd.edu.seu.ajlab1.model.Product;
import bd.edu.seu.ajlab1.repository.ProductDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoDBImplementation implements ProductDao {
    DBConnection db = new DBConnection();
    private Connection connection = db.getConnection();
    private ResultSet resultSet;
    private Statement statement;
    private String tabel_name = "tbl_name";
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
                        productId = Integer.parseInt(resultSet.getString("COL 1"));
                        productName = resultSet.getString("COL 2");
                        quantityPerUnit = resultSet.getString("COL 5");
                        unitPrice = Double.parseDouble(resultSet.getString("COL 6"));
                        unitsInStock = Double.parseDouble(resultSet.getString("COL 7"));
                        unitsOnOrder = Double.parseDouble(resultSet.getString("COL 8"));
                        reorderLevel = Double.parseDouble(resultSet.getString("COL 9"));
                        discontinued = resultSet.getString("COL 10").charAt(0)=='1';
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
}
