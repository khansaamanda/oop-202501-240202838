package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductTableView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class AppJavaFX extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // GANTI "password_kamu" dengan password PostgreSQL di laptopmu
            String url = "jdbc:postgresql://localhost:5432/agripos";
            String user = "postgres";
            String pass = "@sentana12345"; 

            Connection conn = DriverManager.getConnection(url, user, pass);
            
            ProductDAO dao = new ProductDAOImpl(conn);
            ProductService service = new ProductService(dao);
            ProductTableView view = new ProductTableView();
            new ProductController(service, view);

            stage.setScene(new Scene(view, 500, 600));
            stage.setTitle("Agri-POS Week 13 - Database Connected");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showError("Gagal Koneksi Database: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) { launch(args); }
}