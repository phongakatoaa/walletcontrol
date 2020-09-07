package com.toaa.walletcontrol.service;

import com.toaa.walletcontrol.database.CategoryRepository;
import com.toaa.walletcontrol.model.wallet.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SecureUserService secureUserService;

    public Category getById(long id) {
        return categoryRepository.findById(id);
    }

    public Category create(Category category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setUser(secureUserService.getCurrentUser());
        newCategory.setColor(category.getColor());
        return categoryRepository.save(newCategory);
    }
}
