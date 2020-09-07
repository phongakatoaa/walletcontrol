package com.toaa.walletcontrol.controller;

import com.toaa.walletcontrol.model.wallet.Category;
import com.toaa.walletcontrol.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable long id) {
        return categoryService.findById(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.create(category);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Category updateCategory(@Valid @RequestBody Category category) {
        return categoryService.update(category);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getCategories() {
        return categoryService.getAll();
    }
}
