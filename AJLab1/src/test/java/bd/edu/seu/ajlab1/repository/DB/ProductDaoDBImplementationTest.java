package bd.edu.seu.ajlab1.repository.DB;

import bd.edu.seu.ajlab1.model.Product;
import bd.edu.seu.ajlab1.repository.ProductDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoDBImplementationTest {

    private ProductDao productDao = new ProductDaoDBImplementation();

    @Test
    void readAll() {
        List<Product>productList = productDao.readAll();
       productList.forEach(System.out::println);
    }


    @Test
    void createProduct() {

        Product product = new Product(80,"phone",
                "13-pice",23.5,10,
                10,15,true);
        productDao.createProduct(product);

        Product product1 = productDao.readProduct(80);
        Assertions.assertEquals(product1.getProductName(),product.getProductName());
        Assertions.assertEquals(product1.getUnitPrice(),product.getUnitPrice());
    }

    @Test
    void deleteProduct() {
        Product product = productDao.readProduct(80);
        System.out.println(product);
        productDao.deleteProduct(80);
    }

    @Test
    void updateProduct() {
        Product product = new Product(3,"iPhone",
                "13-pice per box",23.5,10,
                10,30,true);
        productDao.updateProduct(product.getProductID(),product);
        Product product1 = productDao.readProduct(3);
        System.out.println(product1);
    }

    @Test
    void readProduct() {
        Product product = productDao.readProduct(3);
        System.out.println(product);
    }
}