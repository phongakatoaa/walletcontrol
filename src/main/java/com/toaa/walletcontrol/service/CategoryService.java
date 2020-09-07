package com.toaa.walletcontrol.service;

import com.toaa.walletcontrol.database.CategoryRepository;
import com.toaa.walletcontrol.model.wallet.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(long id) {
        return categoryRepository.findById(id);
    }

    public Category create(Category category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setColor(category.getColor());
        newCategory.setActive(category.getActive());
        return categoryRepository.save(newCategory);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public List<Category> getAllActive() {
        return categoryRepository.findAllByActive(true);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }
}
