package db.edu.seu;

import db.edu.seu.model.product;
import db.edu.seu.repository.MysqlImplementation;
import db.edu.seu.repository.ProductDAO;

public class main {
    ProductDAO productDAO = new MysqlImplementation();
    public main() {
//        product p = new product(20,"phone",500000,10);
//        System.out.println(productDAO.saveProduct(p));

 //      System.out.println("the product : "+productDAO.read_a_Product(10));

 //       System.out.println("Delete a product : "+productDAO.delete_a_product(10));
        product p = new product(20,"iPhone",500_000,100);
        System.out.println("update product : "+productDAO.updateProduct(p.getId(),p));

    }

    public static void main(String args[])
    {
        new main();
    }
}
