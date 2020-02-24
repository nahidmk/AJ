/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.ajlab1;

import bd.edu.seu.ajlab1.service.ProductService;

/**
 *
 * @author kmhasan
 */
public class Main {
    private ProductService productService;
    
    public Main() {
        productService = new ProductService();
        
//        System.out.println("Discontinued products: " + productService.findAllDiscontinuedProducts().size());
//        System.out.println("Product that need to be reorder : "+productService.findProductsThatNeedToBeReordered().size());
//        System.out.println("Product that sorted by there unit price : ");
//        productService.getProductListSortedByUnitPrice().forEach(System.out::println);
//        System.out.println("\n\n product that sorted by there name: ");
//        productService.getProductListSortedByUnitProductName().forEach(System.out::println);
//        System.out.println("\n\n the price : "+productService.getTotalPriceOfProductsInStock());

//        System.out.println("\n\n create a new product : ");
//        productService.createProduct();

//        System.out.println("\n\n Delete a product : ");
//        productService.deleteProduct();
//
        System.out.println("\n\n Update a product : ");
        productService.updateProduct();

    }


    
    public static void main(String args[]) {
        new Main();
    }
}
