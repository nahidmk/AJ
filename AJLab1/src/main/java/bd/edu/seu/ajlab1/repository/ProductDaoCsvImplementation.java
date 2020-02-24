/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.ajlab1.repository;

import bd.edu.seu.ajlab1.model.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author kmhasan
 */
public class ProductDaoCsvImplementation implements ProductDao {

    private final String CSV_FILENAME = "products.csv";

    @Override
    public List<Product> readAll() {
        try {
            List<Product> productList = Files.lines(Paths.get(CSV_FILENAME))
                    .map(this::getProduct)
                    .filter(product -> product != null)
                    .collect(Collectors.toList());
            return productList;
        } catch (IOException ex) {
            Logger.getLogger(ProductDaoCsvImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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


    @Override
    public void createProduct(Product product) {
        boolean isPresent = false;

        List<String> list = null;
        try {
            list = Files.readAllLines(Paths.get("products.csv"));

            for (String i : list) {
                if (getProductID(i) == product.getProductID()) {
                    isPresent = true;
                }
            }

            if (!isPresent) {
                String discontinued = "0";
                if (product.isDiscontinued()) {
                    discontinued = "1";
                }
                String Product = "\n" + product.getProductID() + "," + product.getProductName() + ",10,8,"
                        + product.getQuantityPerUnit() + "," + product.getUnitPrice() + "," + product.getUnitsInStock()
                        + "," + product.getUnitsOnOrder() + "," + product.getReorderLevel() + "," + discontinued;

                BufferedWriter bw = new BufferedWriter(new FileWriter("products.csv", true));
                bw.write(Product);
                bw.close();
                System.out.println("Insert done....!");
            } else {
                System.out.println("sorry...Change the product ID");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int productId) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            List<String> list = null;
            list = Files.readAllLines(Paths.get("products.csv"));
            for(String s : list)
            {
                if(getProductID(s)!=productId)
                {
                    stringBuilder.append(s+"\n");
                }
            }
             String Product = stringBuilder.toString();
             BufferedWriter bw = new BufferedWriter(new FileWriter("products.csv"));
             bw.write(Product);
             bw.close();
            System.out.println("Delete done...!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(int productId, Product product) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            List<String> list = null;
            list = Files.readAllLines(Paths.get("products.csv"));
            for(String s : list)
            {

                if(getProductID(s)==productId)
                {
                    String discontinued = "0";
                    if(product.isDiscontinued())
                    {
                        discontinued = "1";
                    }
                    s = product.getProductID()+","+product.getProductName()+",10,8,"
                            +product.getQuantityPerUnit()+","+product.getUnitPrice()+","+product.getUnitsInStock()
                            +","+product.getUnitsOnOrder()+","+product.getReorderLevel()+","+discontinued;
                }
                stringBuilder.append(s+"\n");
            }
            String Product = stringBuilder.toString();
            BufferedWriter bw = new BufferedWriter(new FileWriter("products.csv"));
            bw.write(Product);
            bw.close();
            System.out.println("Update Done....!");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public int getProductID(String s)
    {
        String arr[] = s.split("\\,");
        return Integer.parseInt(arr[0]);
    }

}
