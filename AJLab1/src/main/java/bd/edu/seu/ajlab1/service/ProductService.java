/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.ajlab1.service;

import bd.edu.seu.ajlab1.model.Product;
import bd.edu.seu.ajlab1.repository.DB.ProductDaoDBImplementation;
import bd.edu.seu.ajlab1.repository.ProductDao;
import bd.edu.seu.ajlab1.repository.ProductDaoCsvImplementation;
import java.util.ArrayList;
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
        //productDao = new ProductDaoCsvImplementation();
       productDao = new ProductDaoDBImplementation();
    }

    // a sample implementation
    public List<Product> findAllDiscontinuedProducts() {
        List<Product> productList = productDao.readAll()
                .stream()
                .filter((product) -> product.isDiscontinued())
                .collect(Collectors.toList());
        return productList;
    }
    
    // TODO write your code here
    public List<Product> findProductsThatNeedToBeReordered() {
        List<Product> productList = productDao.readAll()
                .stream()
                .filter(p->p.getUnitsInStock()<p.getUnitsOnOrder())
                .collect(Collectors.toList());

        return productList;
    }
    
    // TODO write your code here
    public List<Product> getProductListSortedByUnitPrice() {
        List<Product> productList = productDao.readAll()
                .stream()
                .sorted(Comparator.comparing(Product::getUnitPrice))
                .collect(Collectors.toList());
        return productList;
    }

    // TODO write your code here
    public List<Product> getProductListSortedByUnitProductName() {
        List<Product> productList = productDao.readAll()
                .stream()
                .sorted(Comparator.comparing(Product::getProductName))
                .collect(Collectors.toList());
        return productList;
    }

    // TODO write your code here
    public double getTotalPriceOfProductsInStock() {

        return productDao.readAll()
                .stream()
                .mapToDouble(p-> p.getUnitsInStock() * p.getUnitPrice()).sum();
    }

    public void createProduct()
    {
        Product product = new Product(80,"ball","12-pice",23.5,10,7,10,false);
        productDao.createProduct(product);
    }

    public  void  deleteProduct()
    {
        productDao.deleteProduct(80);
    }

    public void updateProduct()
    {
        Product product = new Product(2,"ball","12-pice",23.5,10,7,10,false);
        productDao.updateProduct(product.getProductID(),product);
    }
}
