# Laporan Praktikum Minggu 12
Topik: Implementasi Konsep Class dan Object dalam Pemrograman Berorientasi Objek

## Identitas
- Nama  : Khansa Amanda Icha Sentana
- NIM   : 240202838
- Kelas : 3IKRA

---

## Tujuan
Setelah mengikuti praktikum ini, mahasiswa diharapkan mampu memahami konsep event-driven programming, membangun antarmuka grafis sederhana menggunakan JavaFX, serta membuat form input data produk. Selain itu, mahasiswa mampu menampilkan daftar produk pada GUI dan mengintegrasikan antarmuka tersebut dengan modul backend yang telah dibuat, yaitu DAO dan Service.
---

## Dasar Teori
1. Pemrograman Berorientasi Objek (Object-Oriented Programming/OOP) merupakan paradigma pemrograman yang berfokus pada penggunaan objek untuk merepresentasikan data dan perilaku.

2. Class adalah cetak biru (blueprint) yang digunakan untuk mendefinisikan atribut dan method dari suatu objek.

3. Object adalah hasil instansiasi dari class yang dapat digunakan untuk menjalankan method dan mengakses atribut.

4. Enkapsulasi bertujuan untuk melindungi data dengan membatasi akses langsung ke atribut melalui penggunaan modifier akses dan method getter/setter.

5. JavaFX merupakan framework Java yang digunakan untuk membangun antarmuka grafis (GUI) yang interaktif dan terstruktur.

---

## Langkah Praktikum
1. Melakukan setup proyek Java menggunakan IDE (Visual Studio Code) dengan struktur folder berbasis Maven serta mengonfigurasi JavaFX agar dapat dijalankan dengan baik.

2. Membuat dan mengimplementasikan class yang dibutuhkan, antara lain class Produk, ProductService, ProductFormView, dan Launcher untuk menerapkan konsep OOP dan pemisahan tugas (separation of concerns).

3. Menulis kode JavaFX untuk membangun antarmuka input produk yang terdiri dari field kode produk, nama produk, harga, stok, tombol tambah produk, serta daftar produk yang ditampilkan.

4. Menjalankan (run) aplikasi untuk menguji fungsi input dan tampilan data produk, kemudian melakukan perbaikan jika terdapat kesalahan logika atau tampilan.

5. Melakukan commit ke repositori Git dengan pesan commit: “Menambahkan form input produk Agri-POS berbasis JavaFX”.

---

## Kode Program
1. package com.upb.agripos.controller;

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
2. package com.upb.agripos.dao;

import java.util.List;

import com.upb.agripos.model.Product;

public interface ProductDAO {
    void insert(Product product) throws Exception;
    Product findByCode(String code) throws Exception;
    List<Product> findAll() throws Exception;
    void update(Product product) throws Exception;
    void delete(String code) throws Exception;
}
3. package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.Product;

public class ProductDAOImpl implements ProductDAO {
    private final Connection connection;

    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Product p) throws Exception {
        String sql = "INSERT INTO products(code, name, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getCode());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.executeUpdate();
        }
    }

    @Override
    public Product findByCode(String code) throws Exception {
        String sql = "SELECT * FROM products WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // PERBAIKAN DI SINI:
                    // Menambahkan rs.getDouble("price") dan rs.getInt("stock")
                    return new Product(
                        rs.getString("code"), 
                        rs.getString("name"),
                        rs.getDouble("price"), 
                        rs.getInt("stock")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // PERBAIKAN DI SINI JUGA:
                // Menambahkan price dan stock agar sesuai constructor baru
                list.add(new Product(
                    rs.getString("code"), 
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ));
            }
        }
        return list;
    }

    @Override
    public void update(Product p) throws Exception {
        String sql = "UPDATE products SET name=?, price=?, stock=? WHERE code=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getCode());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String code) throws Exception {
        String sql = "DELETE FROM products WHERE code=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        }
    }
}
4. package com.upb.agripos.model;

public class Product {
    private String code;
    private String name;
    private double price;
    private int stock;

    // --- PERBAIKAN DI SINI (CONSTRUCTOR) ---
    // Sekarang menerima 4 data: code, name, price, stock
    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    // Setters
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
}
5. package com.upb.agripos.service;

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
6. package com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProductFormView extends VBox {
    public TextField txtCode = new TextField();
    public TextField txtName = new TextField();
    public TextField txtPrice = new TextField();
    public TextField txtStock = new TextField();
    public Button btnAdd = new Button("Tambah Produk");
    public ListView<String> listView = new ListView<>();

    public ProductFormView() {
        this.setPadding(new Insets(20));
        this.setSpacing(15);
        this.setAlignment(Pos.TOP_CENTER);

        Label lblTitle = new Label("FORM INPUT PRODUK AGRI-POS");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(new Label("Kode Produk:"), 0, 0);
        grid.add(txtCode, 1, 0);
        grid.add(new Label("Nama Produk:"), 0, 1);
        grid.add(txtName, 1, 1);
        grid.add(new Label("Harga:"), 0, 2);
        grid.add(txtPrice, 1, 2);
        grid.add(new Label("Stok:"), 0, 3);
        grid.add(txtStock, 1, 3);

        // Styling tombol agar lebar
        btnAdd.setMaxWidth(Double.MAX_VALUE);

        this.getChildren().addAll(lblTitle, grid, btnAdd, new Label("Daftar Produk Terinput:"), listView);
    }
}
7. package com.upb.agripos;

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
8. package com.upb.agripos;

public class Launcher {
    public static void main(String[] args) {
        // Panggil main class utama kamu
        AppJavaFX.main(args);
    }
}
---
## Screenshot
![alt text](<Screenshot (75).png>)

## Analisis
1. Penjelasan Jalannya Kode:
a. Aplikasi dijalankan melalui class Launcher sebagai entry point.
b. Form JavaFX ditampilkan untuk input data produk.
c. Saat tombol Tambah Produk ditekan, data diambil, dibuat menjadi objek produk, lalu ditampilkan ke daftar.

2. Perbedaan Pendekatan dengan Minggu Sebelumnya:
a. Minggu sebelumnya menggunakan input–output berbasis console.
b. Minggu ini menggunakan GUI JavaFX.
c. Struktur program lebih terpisah antara tampilan dan logika.

3. Kendala dan Cara Mengatasinya:

a. Error konfigurasi JavaFX → memperbaiki dependency dan versi Java.

b. Kesalahan input data → menambahkan validasi dan konversi tipe data.
---

## Kesimpulan
Praktikum minggu ini memberikan pemahaman tentang penerapan konsep pemrograman berorientasi objek seperti class, object, dan enkapsulasi dalam pembuatan aplikasi berbasis JavaFX. Dengan penggunaan antarmuka grafis, aplikasi menjadi lebih interaktif dan mudah digunakan. Selain itu, struktur program yang terorganisasi membuat kode lebih mudah dipahami, dipelihara, dan dikembangkan pada tahap selanjutnya.

---

## Tabel
| Artefak Bab 6 | Referensi | Handler GUI | Controller/Service | DAO | Dampak UI/DB |
| :--- | :--- | :--- | :--- | :--- | :--- |
| Use Case | UC-01 Tambah Produk | Tombol Tambah | `ProductController.add()` $\rightarrow$ `ProductService.insert()` | `ProductDAO.insert()` | UI list bertambah + DB insert |
| Activity | AD-01 Tambah Produk | Tombol Tambah | (metode sesuai Anda) | (metode sesuai Anda) | validasi $\rightarrow$ simpan $\rightarrow$ tampil |
| Sequence | SD-01 Tambah Produk | Tombol Tambah | View $\rightarrow$ Controller $\rightarrow$ Service | DAO $\rightarrow$ DB | urutan panggilan sesuai SD |
