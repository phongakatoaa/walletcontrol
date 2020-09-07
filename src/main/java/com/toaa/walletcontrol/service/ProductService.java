package com.toaa.walletcontrol.service;

import com.toaa.walletcontrol.database.CategoryRepository;
import com.toaa.walletcontrol.database.ProductRepository;
import com.toaa.walletcontrol.model.wallet.Category;
import com.toaa.walletcontrol.model.wallet.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SecureUserService secureUserService;

    public Product getById(long id) {
        return productRepository.findById(id);
    }

    public Product create(Product product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setUser(secureUserService.getCurrentUser());
        newProduct.setCategory(product.getCategory());
        return productRepository.save(newProduct);
    }
}
