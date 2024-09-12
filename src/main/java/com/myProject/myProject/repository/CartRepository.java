package com.myProject.myProject.repository;

import com.myProject.myProject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
// CartRepository.java
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.title = :title")
    Optional<Cart> findByTitle(@Param("title") String title);
}
