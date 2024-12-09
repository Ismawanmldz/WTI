package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final List<Product> productList = new ArrayList<>();
    private final Cart cart = new Cart();

    //For Paging
    public List<Product> getPaginatedProducts(int page, int size) {
        if (page < 1 || size <1){
            throw new IllegalArgumentException("Page number and size must be greater than 0.");
        }
        int start = (page - 1) * size;
        // int start = page * size;
        int end = Math.min(start + size, productList.size());
        if (start >= productList.size()) {
            return new ArrayList<>(); // Return empty list if start index exceeds product list size
        }
        return productList.subList(start, end);
    }
    
    //Func Add Product
    public Product addProduct(Product product) {
        productList.add(product);
        return product;
    }

    public Optional<Product> getProductById(Long id) {
        return productList.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    public List<Product> getCartProducts() {
        return cart.getProducts();
    }

    //Func Add to Cart
    public void addToCart(Long productId) {
        Optional<Product> productOptional = getProductById(productId);
        if (productOptional.isPresent()) {
            cart.addProduct(productOptional.get());
        } else {
            throw new RuntimeException("Product with ID " + productId + " not found.");
        }
    }

    // Func remoce product from cart
    public void removeFromCart(Long productId) {
        cart.removeProduct(productId);
    }

    // Func for update the product
    public Product updateProduct(Long id, Product updatedProduct) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setQuantity(updatedProduct.getQuantity());
                return product;
            }
        }
        throw new RuntimeException("Product with ID " + id + " not found.");
    }
    

}
