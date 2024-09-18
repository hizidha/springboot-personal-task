package com.devland.assignment.personaltask.category;

import com.devland.assignment.personaltask.category.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameContainsIgnoreCase(String name);

    Page<Category> findAllByNameContainsIgnoreCase(String optionalName, Pageable pageable);
}