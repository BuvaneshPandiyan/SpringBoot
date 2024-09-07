package com.myProject.myProject.service;

import com.myProject.myProject.model.ClothingItem;
import com.myProject.myProject.repository.ClothingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothingItemService {

    @Autowired
    private ClothingItemRepository clothingItemRepository;

    public List<ClothingItem> getAllClothingItems() {
        return clothingItemRepository.findAll();
    }

    public ClothingItem addClothingItem(ClothingItem clothingItem) {
        return clothingItemRepository.save(clothingItem);
    }
}
