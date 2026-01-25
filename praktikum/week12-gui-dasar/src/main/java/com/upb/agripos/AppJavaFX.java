package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // 1. Inisialisasi Database (JDBC)
            // Pastikan password "1312" sudah benar sesuai setting PostgreSQL Anda
            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos", "postgres", "@sentana12345"
            );
            
            // 2. Inisialisasi Layer
            ProductDAO dao = new ProductDAOImpl(conn);
            ProductService service = new ProductService(dao);
            ProductFormView view = new ProductFormView();
            
            // Controller menghubungkan View dan Service
            new ProductController(service, view);

            // 3. Menampilkan Stage
            Scene scene = new Scene(view, 450, 550);
            primaryStage.setTitle("Agri-POS Sistem Manajemen Produk");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Gagal memulai aplikasi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Gunakan Application.launch untuk memastikan metode terpanggil dengan benar
        Application.launch(args);
    }
}