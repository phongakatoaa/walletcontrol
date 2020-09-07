package com.toaa.walletcontrol.controller;

import com.toaa.walletcontrol.model.wallet.Category;
import com.toaa.walletcontrol.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable long id) {
        return categoryService.getById(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public long createCategory(Category category) {
        return categoryService.create(category).getId();
    }
}
