package com.devland.assignment.personaltask.category;

import com.devland.assignment.personaltask.category.exception.CategoryAlreadyExistException;
import com.devland.assignment.personaltask.category.exception.CategoryNotFoundException;
import com.devland.assignment.personaltask.category.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Page<Category> findAll(Optional<String> optionalName, Pageable pageable) {
        if (optionalName.isPresent()) {
            return this.categoryRepository.findAllByNameContainsIgnoreCase(optionalName.get(), pageable);
        }
        return this.categoryRepository.findAll(pageable);
    }

    public Category getOneByName(String name) {
        if (name != null) {
            return this.categoryRepository.findByNameContainsIgnoreCase(name)
                    .orElseThrow(() -> new CategoryNotFoundException("Category with Name " + name + " not found"));
        }
        return new Category();
    }

    public Category getOneBy(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with ID " + id + " not found"));
    }

    public Category create(Category newCategory) {
        Optional<Category> existingCategory = this.categoryRepository.findByNameContainsIgnoreCase(newCategory.getName());

        if (existingCategory.isPresent()) {
            throw new CategoryAlreadyExistException("Category with name " + newCategory.getName() + " already exist");
        }
        return this.categoryRepository.save(newCategory);
    }

    public Category update(Category updatedCategory) {
        Category existingCategory = this.getOneBy(updatedCategory.getId());
        updatedCategory.setId(existingCategory.getId());

        return this.categoryRepository.save(updatedCategory);
    }

    public void delete(Long id) {
        this.categoryRepository.deleteById(this.getOneBy(id).getId());
    }
}