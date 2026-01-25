package com.upb.agripos.service;

import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;

public class ProductService {
    private ProductDAO dao;

    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }

    public void addProduct(Product p) throws Exception {
        // Validasi Bisnis sederhana
        if (p.getCode() == null || p.getCode().isEmpty() || 
            p.getName() == null || p.getName().isEmpty()) {
            throw new Exception("Kode dan Nama produk tidak boleh kosong!");
        }
        if (p.getPrice() <= 0) {
            throw new Exception("Harga harus lebih besar dari 0!");
        }
        
        // Panggil DAO untuk simpan ke Database
        dao.insert(p);
    }

    // SAYA UBAH NAMANYA DI SINI JADI 'findAll' AGAR COCOK DENGAN CONTROLLER
    public List<Product> findAll() throws Exception {
        return dao.findAll();
    }
}