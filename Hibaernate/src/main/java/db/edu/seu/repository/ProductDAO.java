package db.edu.seu.repository;

import db.edu.seu.model.product;

public interface ProductDAO {
    public product saveProduct(product p);
    public product read_a_Product(long id);
    public product delete_a_product(long id);
    public product updateProduct(long id, product p);
}
