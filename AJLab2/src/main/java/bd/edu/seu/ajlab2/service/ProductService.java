/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.ajlab2.service;

import bd.edu.seu.ajlab2.exception.ProductAlreadyExistsException;
import bd.edu.seu.ajlab2.model.Product;
import bd.edu.seu.ajlab2.repository.ProductDao;
import bd.edu.seu.ajlab2.repository.ProductDaoMySQLImplementation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kmhasan
 */
public class ProductService {
    
    private ProductDao productDao;
    
    public ProductService() {
        // we can switch out the implementation just by changing one line here
        // that's the beauty of using DAO pattern here
//        productDao = new ProductDaoCsvImplementation();
        productDao = new ProductDaoMySQLImplementation();
    }

    public List<Product> readAll() {
        return productDao.readAll();
    }
    
    public Product readProduct(int productId) {
        return productDao.readProduct(productId);
    }
    
    // a sample implementation
    public List<Product> findAllDiscontinuedProducts() {
        List<Product> productList = productDao.readAll()
                .stream()
                .filter(Product::isDiscontinued)
                .collect(Collectors.toList());
        return productList;
    }

    // TODO write your code here
    public List<Product> findProductsThatNeedToBeReordered() {
        List<Product> productList = productDao.readAll()
                .stream()
                .filter(product -> product.getUnitsInStock() < product.getReorderLevel())
                .collect(Collectors.toList());
        return productList;
    }

    // TODO write your code here
    public List<Product> getProductListSortedByUnitPrice() {
        List<Product> productList = productDao.readAll();
        productList = productList
                .stream()
                .sorted(Comparator.comparing(Product::getUnitPrice))
                .collect(Collectors.toList());
        return productList;
    }

    // TODO write your code here
    public List<Product> getProductListSortedByProductName() {
        List<Product> productList = productDao.readAll()
                .stream()
                .parallel()
                .sorted(Comparator.comparing(Product::getProductName)
                        .thenComparing(Product::getUnitPrice))
                .collect(Collectors.toList());
        return productList;
    }

    // TODO write your code here
    public double getTotalPriceOfProductsInStock() {
        return 0;
    }

    public Product createProduct(Product product)
    {
        try {
            return productDao.createProduct(product);
        } catch (ProductAlreadyExistsException e) {
            e.printStackTrace();
        }
        return null;

    }


    public List<Product> createProducts(List<Product> productList)
    {
        return productDao.createProducts(productList);
    }


    public Product deleteProduct(int productId)
    {
        return productDao.deleteProduct(productId);
    }

    public Product updateProduct(int productId, Product product)
    {
        return productDao.updateProduct(productId,product);
    }
    public List<Product> deleteAll()
    {
        return productDao.deleteAll();
    }
    public int write_and_read_from_CSV_to_Mysql(StringBuilder stringBuilder)
    {
        return productDao.write_and_read_from_CSV_to_Mysql(stringBuilder);
    }

}
