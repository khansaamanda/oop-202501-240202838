# Laporan Praktikum Minggu 13
Topik: 

## Identitas
- Nama  : Khansa Ananda Icha Sentana
- NIM   : 240202838
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa mampu memahami dan menerapkan konsep pemrograman berorientasi objek (OOP) pada Java, khususnya penggunaan class, object, enkapsulasi, serta pemisahan layer (model, DAO, service, dan controller), serta mampu mengintegrasikan aplikasi JavaFX dengan database untuk melakukan operasi CRUD (Create, Read, Update, Delete) pada data produk.

---

## Dasar Teori
1. Class merupakan cetak biru (blueprint) yang digunakan untuk mendefinisikan atribut dan method dari suatu objek.

2. Object adalah hasil instansiasi dari class yang merepresentasikan entitas nyata dalam program.

3. Enkapsulasi digunakan untuk melindungi data dengan cara membatasi akses langsung ke atribut melalui modifier akses dan method getter serta setter.

4. JavaFX merupakan framework Java yang digunakan untuk membangun antarmuka pengguna (GUI) yang interaktif.

5. Koneksi database memungkinkan aplikasi melakukan pengelolaan data secara permanen melalui operasi CRUD (Create, Read, Update, Delete).

---

## Langkah Praktikum
1. Menyiapkan environment Java, JavaFX, dan koneksi database pada IDE Visual Studio Code.

2. Membuat struktur package (model, dao, service, controller, view) dan mengimplementasikan class Product dengan enkapsulasi.

3. Mengembangkan ProductDAO, ProductService, dan ProductController untuk menangani proses CRUD data produk.

4. Mendesain antarmuka JavaFX berupa form input dan TableView, lalu menjalankan aplikasi untuk pengujian.

5. Melakukan commit dengan pesan: “Implementasi JavaFX CRUD Produk dengan Koneksi Database”.

---

## Kode Program
1. package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductTableView;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

public class ProductController {
    private ProductService service;
    private ProductTableView view;

    public ProductController(ProductService service, ProductTableView view) {
        this.service = service;
        this.view = view;
        loadData();

        // Lambda Expressions untuk event handling
        view.btnAdd.setOnAction(e -> handleAdd());
        view.btnDelete.setOnAction(e -> handleDelete());
    }

    private void loadData() {
        try {
            view.table.setItems(FXCollections.observableArrayList(service.getAllProducts()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleAdd() {
        try {
            Product p = new Product(
                view.txtCode.getText(), view.txtName.getText(),
                Double.parseDouble(view.txtPrice.getText()),
                Integer.parseInt(view.txtStock.getText())
            );
            service.addProduct(p);
            loadData();
            view.txtCode.clear(); view.txtName.clear(); view.txtPrice.clear(); view.txtStock.clear();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    private void handleDelete() {
        Product selected = view.table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                service.removeProduct(selected.getCode());
                loadData();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Gagal hapus: " + e.getMessage()).show();
            }
        }
    }
}
2. package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import java.util.List;

public interface ProductDAO {
    void insert(Product p) throws Exception;
    void delete(String code) throws Exception;
    List<Product> findAll() throws Exception;
}
3. package com.upb.agripos.model;

public class Product {
    private String code;
    private String name;
    private double price;
    private int stock;

    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}
4. package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;
import java.util.List;

public class ProductService {
    private ProductDAO dao;

    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }

    public void addProduct(Product p) throws Exception {
        dao.insert(p);
    }

    public void removeProduct(String code) throws Exception {
        dao.delete(code);
    }

    public List<Product> getAllProducts() throws Exception {
        return dao.findAll();
    }
}
5. package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;
import java.util.List;

public class ProductService {
    private ProductDAO dao;

    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }

    public void addProduct(Product p) throws Exception {
        dao.insert(p);
    }

    public void removeProduct(String code) throws Exception {
        dao.delete(code);
    }

    public List<Product> getAllProducts() throws Exception {
        return dao.findAll();
    }
}
6. package com.upb.agripos.view;

import com.upb.agripos.model.Product;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class ProductTableView extends VBox {
    public TextField txtCode = new TextField(), txtName = new TextField();
    public TextField txtPrice = new TextField(), txtStock = new TextField();
    public Button btnAdd = new Button("Tambah"), btnDelete = new Button("Hapus Terpilih");
    public TableView<Product> table = new TableView<>();

    @SuppressWarnings("unchecked")
    public ProductTableView() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(10);
        form.add(new Label("Kode:"), 0, 0); form.add(txtCode, 1, 0);
        form.add(new Label("Nama:"), 0, 1); form.add(txtName, 1, 1);
        form.add(new Label("Harga:"), 0, 2); form.add(txtPrice, 1, 2);
        form.add(new Label("Stok:"), 0, 3); form.add(txtStock, 1, 3);

        TableColumn<Product, String> c1 = new TableColumn<>("Kode");
        c1.setCellValueFactory(new PropertyValueFactory<>("code"));
        TableColumn<Product, String> c2 = new TableColumn<>("Nama");
        c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Double> c3 = new TableColumn<>("Harga");
        c3.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Product, Integer> c4 = new TableColumn<>("Stok");
        c4.setCellValueFactory(new PropertyValueFactory<>("stock"));

        table.getColumns().addAll(c1, c2, c3, c4);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        HBox actions = new HBox(10, btnAdd, btnDelete);
        this.getChildren().addAll(new Label("AGRI-POS MASTER DATA"), form, actions, table);
    }
}
7. package com.upb.agripos;

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
8. package com.upb.agripos;

public class Launcher {
    public static void main(String[] args) {
        AppJavaFX.main(args);
    }
}


---

## Hasil Eksekusi
![alt text](<Screenshot (78).png>)
---

## Analisis
1. Kode berjalan dengan alur controller → service → DAO → database, di mana data produk diproses dan ditampilkan kembali melalui JavaFX (TableView).

2. Perbedaan dengan minggu sebelumnya adalah aplikasi sudah terhubung ke database dan menerapkan konsep CRUD dengan struktur layer yang lebih rapi.

3. Kendala yang dihadapi berupa error koneksi database dan data tidak langsung tampil, yang diatasi dengan memperbaiki konfigurasi koneksi dan melakukan refresh data.
---

## Kesimpulan
Dengan menerapkan konsep pemrograman berorientasi objek, pemisahan layer, serta integrasi JavaFX dengan database, aplikasi yang dibangun menjadi lebih terstruktur, mudah dipahami, dan mampu mengelola data produk secara permanen melalui operasi CRUD.

---

## Tabel
| Artefak Desain | Referensi | Handler GUI (View) | Controller/Service | DAO / Database | Dampak Sistem |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Use Case** | UC-01 Tambah Produk | Tombol Tambah | `controller.add()` | `dao.insert()` | Record baru bertambah |
| **Use Case** | UC-02 Hapus Produk | Tombol Hapus & Klik Tabel | `controller.delete()` | `dao.delete(code)` | Record hilang dari DB & UI |
| **Component** | UI Tampilan Data | `TableView` (Grid) | `ObservableList` | `ResultSet` (Loop) | Data tampil per kolom |
| **Error Handling** | Dependency Missing | `pom.xml` Config | Open Correct Folder | - | Library terdeteksi |
