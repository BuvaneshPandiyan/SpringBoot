package com.myProject.myProject.service;

import com.myProject.myProject.model.Cart;
import com.myProject.myProject.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Get all cart items
    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    // Add a new cart item or increment the quantity if it already exists
    public Cart addCartItem(Cart cart) {
        Optional<Cart> existingCartItem = cartRepository.findByTitle(cart.getTitle());

        if (existingCartItem.isPresent()) {
            Cart updatedCartItem = existingCartItem.get();
            updatedCartItem.setQuantity(updatedCartItem.getQuantity() + cart.getQuantity()); // Increment quantity
            return cartRepository.save(updatedCartItem);
        } else {
            cart.setQuantity(1); // Default to 1 if it's a new item
            return cartRepository.save(cart);
        }
    }

    // Update cart item (size, quantity, etc.)
    public Cart updateCartItem(Long id, Map<String, Object> updates) {
        Cart existingCartItem = cartRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Update quantity if present
        if (updates.containsKey("quantity")) {
            int quantity = (int) updates.get("quantity");
            if (quantity > 0) {
                existingCartItem.setQuantity(quantity);
            } else {
                // If quantity is 0 or less, remove the item
                cartRepository.deleteById(id);
                return null;
            }
        }

        // Update size if present
        if (updates.containsKey("size")) {
            String size = (String) updates.get("size");
            existingCartItem.setSize(size);
        }

        // Save and return the updated cart item
        return cartRepository.save(existingCartItem);
    }

    // Remove a cart item by ID
    public void removeCartItem(Long id) {
        cartRepository.deleteById(id);
    }
}
