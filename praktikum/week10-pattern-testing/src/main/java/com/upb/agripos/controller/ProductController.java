package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class ProductController {
    private Product product;
    private ConsoleView view;
    
    public ProductController(Product product, ConsoleView view) {
        this.product = product;
        this.view = view;
    }
    
    public void updateView() {
        view.displayProduct(product.getId(), product.getName());
    }
    
    public void setProductName(String name) {
        product.setName(name);
    }
    
    public String getProductName() {
        return product.getName();
    }
}