package com.myProject.myProject.controller;

import com.myProject.myProject.model.ClothingItem;
import com.myProject.myProject.service.ClothingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clothing-items")
@CrossOrigin(origins = "http://localhost:3000")
public class ClothingItemController {

    @Autowired
    private ClothingItemService clothingItemService;

    @GetMapping
    public List<ClothingItem> getAllClothingItems() {
        return clothingItemService.getAllClothingItems();
    }

    @PostMapping
    public ClothingItem addClothingItem(@RequestBody ClothingItem clothingItem) {
        return clothingItemService.addClothingItem(clothingItem);
    }
}
