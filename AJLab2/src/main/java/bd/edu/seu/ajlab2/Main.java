/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.ajlab2;

import bd.edu.seu.ajlab2.model.Product;
import bd.edu.seu.ajlab2.service.ProductService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author kmhasan
 */
public class Main {
    private ProductService productService;
    
    public Main() {
        productService = new ProductService();
        
//        productService.readAll()
//                .stream()
//                .forEach(System.out::println);
//        System.out.println("Total products: " + productService.readAll().size());
//
//        System.out.println("Searching for product: " + 1);
//        System.out.println(productService.readProduct(1));
//
//        System.out.println("Discontinued products: " + productService.findAllDiscontinuedProducts().size());
//        System.out.println("create Product : ");
//        Product product = new Product(91,"sunglasses","one per box",50,20,39,8,false);
//        System.out.println(productService.createProduct(product));


//        System.out.println("delete product : ");
//        System.out.println(productService.deleteProduct(91));


//        System.out.println("delete all");
//          productService.deleteAll()
//          .stream()
//          .forEach(System.out::println);


//        List<Product>productList = new ArrayList<>();
//        IntStream.range(1,78).forEach(i->{
//            Product product = new Product(i,"G-shok","one per box",29,10,22,9,true);
//            productList.add(product);
//
//        });
//
//        productService.createProducts(productList).stream().forEach(System.out::println);

        StringBuilder stringBuilder = new StringBuilder();

        long start = System.currentTimeMillis();
        IntStream.range(1,70_000).forEach(i-> {
                    Product product = new Product(i, "G-shok", "one per box", 29, 10, 22, 9, true);

                        String discontinued = "0";
                        if (product.isDiscontinued()) {
                            discontinued = "1";
                        }
                        String Products = product.getProductID() + "," + product.getProductName() + ",10,8,"
                                + product.getQuantityPerUnit() + "," + product.getUnitPrice() + "," + product.getUnitsInStock()
                                + "," + product.getUnitsOnOrder() + "," + product.getReorderLevel() + "," + discontinued + "\n";
                        stringBuilder.append(Products);
                });
        System.out.println("String builder is ready...");
        System.out.println("row count : "+productService.write_and_read_from_CSV_to_Mysql(stringBuilder));
        long end = System.currentTimeMillis();
        System.out.println("total time : "+((int)end-(int)start)/1000.0);

//        System.out.println("update product : ");
//        Product product = new Product(88,"G-shok","one per box",29,10,22,9,true);
//        System.out.println(productService.updateProduct(product.getProductID(),product));


        // TODO add necessary lines to produce outputs for the other service methods
//        List<Product> productsToBeReorderedList = productService.findProductsThatNeedToBeReordered();
//        System.out.println("Products to be reordered");
//        productsToBeReorderedList.stream().forEach(System.out::println);
    }
    
    public static void main(String args[]) {
        new Main();
    }
}
