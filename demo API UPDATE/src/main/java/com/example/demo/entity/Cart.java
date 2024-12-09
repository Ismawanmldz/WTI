package com.example.demo.entity;


import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getId().equals(product.getId())) {
                // If the product already exists in the cart, increase its quantity
                p.setQuantity(p.getQuantity() + 1);
                return;
            }
        }
        // If the product is not in the cart, add it with a quantity of 1
        product.setQuantity(1);
        products.add(product);
    }

    //Func remove product
    public void removeProduct(Long productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }

    public Product getProduct(Long productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null; // Return null if the product is not found
    }

    public List<Product> getProducts() {
        return products;
    }
}
