package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;
import javafx.scene.control.Alert;

public class ProductController {
    private ProductService service;
    private ProductFormView view;

    public ProductController(ProductService service, ProductFormView view) {
        this.service = service;
        this.view = view;
        initControl();
        
        // FITUR TAMBAHAN: Load data lama saat aplikasi dibuka
        loadData();
    }

    private void initControl() {
        view.btnAdd.setOnAction(event -> handleAddProduct());
    }

    // Method untuk mengambil data dari database dan menampilkannya di layar
    private void loadData() {
        try {
            view.listView.getItems().clear(); // Bersihkan list dulu
            for (Product p : service.findAll()) {
                view.listView.getItems().add(p.getCode() + " - " + p.getName() + " [Rp" + p.getPrice() + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleAddProduct() {
        try {
            String code = view.txtCode.getText();
            String name = view.txtName.getText();
            double price = Double.parseDouble(view.txtPrice.getText());
            int stock = Integer.parseInt(view.txtStock.getText());

            // --- PERBAIKAN UTAMA DI SINI ---
            // Masukkan 4 parameter: code, name, price, stock
            Product p = new Product(code, name, price, stock);
            
            // Panggil Service
            service.addProduct(p);

            // Update UI (tambah ke list tanpa perlu restart)
            view.listView.getItems().add(p.getCode() + " - " + p.getName() + " [Rp" + p.getPrice() + "]");
            
            // Bersihkan Form
            view.txtCode.clear();
            view.txtName.clear();
            view.txtPrice.clear();
            view.txtStock.clear();

            new Alert(Alert.AlertType.INFORMATION, "Produk berhasil ditambahkan!").show();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Harga dan Stok harus berupa angka!").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}