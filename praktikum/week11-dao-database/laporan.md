# Laporan Praktikum Minggu 11
Topik: Penerapan Data Access Object (DAO) untuk Operasi CRUD pada Database Menggunakan Java dan JDBC

## Identitas
- Nama  : Khansa Amanda Icha Sentana
- NIM   : 240202838
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa mampu memahami dan menerapkan konsep Data Access Object (DAO) pada pemrograman Java, mengimplementasikan operasi CRUD (Create, Read, Update, Delete) menggunakan database, serta menguji koneksi dan manipulasi data melalui pemisahan antara model, DAO interface, dan DAO implementation secara terstruktur.

---

## Dasar Teori
1. Data Access Object (DAO) adalah pola desain yang digunakan untuk memisahkan logika akses data dari logika bisnis aplikasi.

2. Operasi CRUD (Create, Read, Update, Delete) merupakan operasi dasar dalam pengelolaan data pada database.

3. JDBC (Java Database Connectivity) digunakan sebagai penghubung antara aplikasi Java dengan database.

4. Class Model merepresentasikan struktur data yang disimpan di dalam database.

5. Pemisahan interface dan implementasi DAO bertujuan meningkatkan fleksibilitas, kemudahan pemeliharaan, dan keterbacaan kode.

---

## Langkah Praktikum
1. Menyiapkan project Java (Maven), database, dan konfigurasi awal.

2. Membuat class model Product sesuai struktur tabel.

3. Membuat ProductDAO dan ProductDAOImpl untuk operasi CRUD menggunakan JDBC.

4. Membuat MainDAOTest untuk menguji proses CRUD.

5. Menjalankan program dan memastikan koneksi serta operasi berhasil.
a. File yang dibuat:
Product.java, ProductDAO.java, ProductDAOImpl.java, MainDAOTest.java, Products.sql
b. Commit message:
Add Product model, Implement Product DAO CRUD, Add DAO test

---

## Kode Program
1. CREATE TABLE products (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price NUMERIC(12, 2) NOT NULL,
    stock INTEGER NOT NULL
);
2. package com.upb.agripos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class MainDAOTest {
    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/agripos",
            "postgres",
            "1312"
        );

        System.out.println("Database connected!");
        ProductDAO dao = new ProductDAOImpl(conn);

        // Bersihkan data lama agar tidak error duplicate key saat insert ulang
        dao.delete("P01");

        // 1. Insert
        System.out.println("Inserting product...");
        dao.insert(new Product("P01", "Pupuk Organik", 25000, 10));
        
        // 2. Update
        System.out.println("Updating product...");
        dao.update(new Product("P01", "Pupuk Organik Premium", 30000, 8));

        // 3. Find By Code
        Product p = dao.findByCode("P01");
        System.out.println("Found: " + p.getName() + " | Price: " + p.getPrice());

        // 4. Find All
        System.out.println("All Products:");
        List<Product> list = dao.findAll();
        for (Product prod : list) {
            System.out.println("- " + prod.getName() + " (" + prod.getStock() + ")");
        }

        // 5. Delete
        System.out.println("Deleting product...");
        dao.delete("P01");
        
        conn.close();
        System.out.println("\ncredit by: 240202838 - khansa amanda icha sentana");
    }
}
3. package com.upb.agripos.dao;

import java.util.List;

import com.upb.agripos.model.Product;

public interface ProductDAO {
    void insert(Product product) throws Exception;
    Product findByCode(String code) throws Exception;
    List<Product> findAll() throws Exception;
    void update(Product product) throws Exception;
    void delete(String code) throws Exception;
}
4. package com.upb.agripos.dao;

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
                    return new Product(
                        rs.getString("code"), rs.getString("name"),
                        rs.getDouble("price"), rs.getInt("stock")
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
                list.add(new Product(
                    rs.getString("code"), rs.getString("name"),
                    rs.getDouble("price"), rs.getInt("stock")
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
5. package com.upb.agripos.model;

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

    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    // Setters (Penting untuk proses Update)
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
}
6. package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class MainDAOTest {
    public static void main(String[] args) throws Exception {
        // 1. Koneksi ke database (Ganti password sesuai PostgreSQL kamu)
        Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/agripos", "postgres", "@sentana12345"
        );

        ProductDAO dao = new ProductDAOImpl(conn);

        // 2. CREATE (Tambah Data)  
        System.out.println("Menambah produk...");
        dao.insert(new Product("P01", "Pupuk Organik", 25000, 10));

        // 3. READ & UPDATE (Cari dan Ubah)
        Product p = dao.findByCode("P01");
        if (p != null) {
            System.out.println("Data ditemukan: " + p.getName());
            p.setName("Pupuk Organik Premium");
            dao.update(p);
            System.out.println("Data berhasil di-update!");
        }

        // 4. DELETE (Hapus)
        // dao.delete("P01"); 

        conn.close();
        System.out.println("Koneksi ditutup.");
    }
}
7. <project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.upb.agripos</groupId>
    <artifactId>week11-dao-database</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.2</version>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.10.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.10.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
            </plugin>
        </plugins>
    </build>
</project>
---

## Hasil Eksekusi
![alt text](<Screenshot 2026-01-25 150431.png>)
---

## Analisis
1. Cara kerja kode:
Program dijalankan melalui MainDAOTest yang memanggil method CRUD pada ProductDAOImpl untuk mengakses database menggunakan JDBC.

2. Perbedaan dengan minggu sebelumnya:
Minggu ini menggunakan pola DAO dengan pemisahan akses database, sedangkan minggu sebelumnya masih menggabungkan logika program dan data.

3. Kendala dan solusi:
Kendala koneksi dan query SQL → diatasi dengan pengecekan konfigurasi JDBC dan struktur tabel.
---

## Kesimpulan
Dengan menerapkan pola Data Access Object (DAO), program menjadi lebih terstruktur, mudah dipelihara, serta memisahkan logika akses database dari logika utama aplikasi.

---

## Tabel


