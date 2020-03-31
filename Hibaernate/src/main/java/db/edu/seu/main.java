package db.edu.seu;

import db.edu.seu.model.Supplier;
import db.edu.seu.model.priceHistory;
import db.edu.seu.model.product;
import db.edu.seu.repository.GenericMysqlImplementation;
import db.edu.seu.repository.GenericsDAO;
import db.edu.seu.repository.MysqlImplementation;
import db.edu.seu.repository.ProductDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {
//    ProductDAO productDAO = new MysqlImplementation();
    GenericsDAO<product,Long> productDAO = new GenericMysqlImplementation<>(product.class);
    GenericsDAO<Supplier,Long> SupplierDAO = new GenericMysqlImplementation<>(Supplier.class);
    GenericsDAO<priceHistory,Integer> priceHistoryDAO = new GenericMysqlImplementation<>(priceHistory.class);
    private product createProduct(int id, String name, double price, double stock) {
        product product1 = new product();
        product1.setId(id);
        product1.setName(name);
        product1.setUnitprice(price);
        product1.setUnitStock(stock);
        product1.setDiscontinue(false);
        product1.setSupplierList(new ArrayList<>());
        return product1;
    }

    Supplier createSupplier(int id, String name, String address) {
        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setName(name);
        supplier.setAddress(address);
        return supplier;
    }

    priceHistory createPriceHistory(product p, LocalDateTime localDateTime,double price)
    {
        priceHistory history = new priceHistory();
        history.setProduct(p);
        history.setLocalDateTime(localDateTime);
        history.setPricePerUnit(price);
        return history;
    }

    public main() {
//        product p = new product(20,"phone",500000,10);
//        System.out.println(productDAO.saveProduct(p));

 //      System.out.println("the product : "+productDAO.read_a_Product(10));

 //       System.out.println("Delete a product : "+productDAO.delete_a_product(10));

    //    product p = new product(20,"iPhone",500_000,100,new ArrayList<>());
 //       System.out.println("update product : "+productDAO.updateProduct(p.getId(),p));

//        product product1 = createProduct(4, "Sumsung", 50_000, 10);
//        product product2 = createProduct(998, "Casio G-Shock", 20_00, 10);
////
//        Supplier supplier1 = createSupplier(14, "nahid", "mohakhali");
//        Supplier supplier2 = createSupplier(15, "JB", "Nowhere");
//        Supplier supplier3 = createSupplier(16, "arif", "anywhere");
//
//
//        product1.setSupplierList(Arrays.asList(supplier1,supplier2));
//        product2.setSupplierList(Arrays.asList(supplier3));
//
//
//        LocalDateTime ldt = LocalDateTime.now();
//        priceHistory priceHistory1 = createPriceHistory(product1,ldt,50_000);
//        priceHistory priceHistory2 = createPriceHistory(product2,ldt,20_00);


//        SupplierDAO.findAll().stream().forEach(System.out::println);
//        SupplierDAO.save(supplier1);
//        SupplierDAO.save(supplier2);
//        SupplierDAO.save(supplier3);
//         productDAO.save(product1);
//        productDAO.save(product2);
//        productDAO.update((long)998,product2);
//        priceHistoryDAO.save(priceHistory1);
//        priceHistoryDAO.save(priceHistory2);
//        SupplierDAO.save(supplier3);
//        productDAO.findAll().stream().forEach(System.out::println);
//        priceHistoryDAO.findAll().stream().forEach(System.out::println);
//        SupplierDAO.deleteAll();
//        productDAO.deleteAll();
//        priceHistoryDAO.deleteAll();
//        System.out.println(SupplierDAO.findById((long)12));
//          productDAO.deleteById((long)4);
        SupplierDAO.deleteById((long)16);
//        priceHistoryDAO.deleteById(1);
 //       System.out.println(SupplierDAO.update((long)14,supplier1));


    }

    public static void main(String args[])
    {
        new main();
    }
}
