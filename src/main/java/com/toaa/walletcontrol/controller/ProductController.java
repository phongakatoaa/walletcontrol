package com.toaa.walletcontrol.controller;

import com.toaa.walletcontrol.model.wallet.Product;
import com.toaa.walletcontrol.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Product createProduct(Product product) {
        return productService.create(product);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable long id){
        return productService.getById(id);
    }
}
