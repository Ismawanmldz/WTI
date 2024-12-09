package com.example.demo.controller;


import com.example.demo.entity.Product;
import com.example.demo.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/products")
    public List<Product> getPaginatedProducts(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "100") int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be >= 0 and size must be > 0.");
        }
        return cartService.getPaginatedProducts(page, size);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return cartService.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return cartService.updateProduct(id, updatedProduct);
    }

    // Func Manage Cart
    @GetMapping("/cart")
    public List<Product> getCartProducts() {
        return cartService.getCartProducts();
    }

    @PostMapping("/cart/{productId}")
    public String addToCart(@PathVariable Long productId) {
        cartService.addToCart(productId);
        return "Product added to cart.";
    }

    @DeleteMapping("/cart/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return "Product removed from cart.";
    }
}
