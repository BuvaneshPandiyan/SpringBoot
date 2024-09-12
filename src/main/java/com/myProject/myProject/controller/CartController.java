package com.myProject.myProject.controller;

import com.myProject.myProject.model.Cart;
import com.myProject.myProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    @Autowired
    private CartService cartService;

    // Get all cart items
    @GetMapping
    public List<Cart> getAllCartItems() {
        return cartService.getAllCartItems();
    }

    // Add a new cart item or update the quantity if it already exists
    @PostMapping
    public Cart addCartItem(@RequestBody Cart cart) {
        return cartService.addCartItem(cart);
    }

    // Update cart item (size, quantity, etc.)
    @PatchMapping("/{id}")
    public Cart updateCartItem(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return cartService.updateCartItem(id, updates);
    }

    // Remove a cart item by ID
    @DeleteMapping("/{id}")
    public void removeCartItem(@PathVariable Long id) {
        cartService.removeCartItem(id);
    }
}
