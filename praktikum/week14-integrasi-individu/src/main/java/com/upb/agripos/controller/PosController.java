package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.PosView;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

public class PosController {
    private ProductService pService;
    private CartService cService;
    private PosView view;

    public PosController(ProductService ps, CartService cs, PosView v) {
        this.pService = ps;
        this.cService = cs;
        this.view = v;

        // Load data saat aplikasi pertama kali dibuka
        loadProducts();
        
        // Pasang Event Handler (Logika Tombol)
        initHandlers();
    }

    private void initHandlers() {
        // 1. TOMBOL SIMPAN PRODUK
        view.btnAddProd.setOnAction(e -> {
            try {
                Product p = new Product(
                    view.txtCode.getText(),
                    view.txtName.getText(),
                    Double.parseDouble(view.txtPrice.getText()),
                    Integer.parseInt(view.txtStock.getText())
                );
                pService.addProduct(p);
                
                loadProducts(); // Refresh tabel
                clearForm();    // Bersihkan kotak input
                
                new Alert(Alert.AlertType.INFORMATION, "Produk berhasil disimpan!").show();
            } catch (Exception ex) {
                showError("Gagal Simpan: " + ex.getMessage());
            }
        });

        // 2. TOMBOL HAPUS PRODUK
        view.btnDeleteProd.setOnAction(e -> {
            try {
                Product s = view.table.getSelectionModel().getSelectedItem();
                if (s != null) {
                    pService.deleteProduct(s.getCode());
                    loadProducts();
                    new Alert(Alert.AlertType.INFORMATION, "Produk berhasil dihapus!").show();
                } else {
                    showError("Pilih produk di tabel dulu!");
                }
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });

        // 3. TOMBOL TAMBAH KE KERANJANG
        view.btnAddToCart.setOnAction(e -> {
            try {
                Product s = view.table.getSelectionModel().getSelectedItem();
                
                // Validasi: Harus pilih produk & isi jumlah
                if (s == null) throw new Exception("Pilih produk di tabel dulu!");
                if (view.txtQty.getText().isEmpty()) throw new Exception("Isi jumlah (Qty) dulu!");

                int qty = Integer.parseInt(view.txtQty.getText());
                
                cService.addToCart(s, qty);
                updateCartUI();
                
                view.txtQty.clear(); // Bersihkan input qty
            } catch (NumberFormatException ex) {
                showError("Jumlah (Qty) harus berupa angka!");
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });
    }

    // --- METHOD BANTUAN ---

    private void loadProducts() {
        try {
            // PERBAIKAN DI SINI:
            // Menggunakan 'getAllProducts()' menggantikan 'findAll()' yang error
            view.table.setItems(FXCollections.observableArrayList(pService.getAllProducts()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCartUI() {
        view.cartList.getItems().clear();
        // Tampilkan item belanjaan
        cService.getCart().getItems().forEach(item -> 
            view.cartList.getItems().add(item.toString())
        );
        // Tampilkan Total Harga
        view.lblTotal.setText("Total: Rp " + cService.getCart().getTotalPrice());
    }

    private void clearForm() {
        view.txtCode.clear();
        view.txtName.clear();
        view.txtPrice.clear();
        view.txtStock.clear();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();
    }
}