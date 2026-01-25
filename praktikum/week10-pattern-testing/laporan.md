# Laporan Praktikum Minggu 10
Topik: Penerapan Object-Oriented Programming (OOP) dengan Design Pattern MVC dan Singleton

## Identitas
- Nama  : Khansa Amanda Icha Sentana
- NIM   : 240202838
- Kelas : 3IKRA

---

## Tujuan
Tujuan praktikum minggu ini adalah agar mahasiswa memahami dan mampu menerapkan konsep Object-Oriented Programming (OOP) menggunakan bahasa Java, khususnya penerapan design pattern MVC (Model–View–Controller) dan Singleton. Melalui praktikum ini, mahasiswa diharapkan dapat membuat class dengan prinsip enkapsulasi, memisahkan logika program ke dalam model, view, dan controller, serta mengintegrasikan seluruh komponen tersebut dalam sebuah aplikasi sederhana yang dapat dijalankan dan diuji dengan baik.

---

## Dasar Teori
1. Object-Oriented Programming (OOP) adalah paradigma pemrograman yang berfokus pada penggunaan objek untuk merepresentasikan data dan perilaku dalam program.

2. Class merupakan cetak biru (blueprint) yang mendefinisikan atribut dan metode, sedangkan object adalah instansiasi dari class tersebut.

3. Enkapsulasi digunakan untuk melindungi data dengan membatasi akses langsung ke atribut melalui getter dan setter.

4. Model–View–Controller (MVC) adalah pola desain yang memisahkan data (Model), tampilan (View), dan logika pengendali (Controller) agar kode lebih terstruktur dan mudah dipelihara.

5. Singleton Pattern memastikan sebuah class hanya memiliki satu instance dan menyediakan akses global terhadap instance tersebut.

---

## Langkah Praktikum
1. Melakukan setup project Java dan menyiapkan struktur MVC (model, view, controller).

2. Membuat file Product.java, ConsoleView.java, ProductController.java, DatabaseConnection.java, dan AppMVC.java.

3. Menjalankan (run) program untuk menguji integrasi MVC dan Singleton.

4. Melakukan commit dengan pesan: “Implement MVC and Singleton (Week 10)”.

---

## Kode Program
1. package com.upb.agripos.config;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    
    private DatabaseConnection() {
        // Private constructor untuk Singleton
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public void connect() {
        System.out.println("Database connected successfully");
    }
}
2. package com.upb.agripos.controller;

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
3. package com.upb.agripos.model;

public class Product {
    // 1. Variabel (Attributes)
    private String id;
    private String name;

    // 2. Constructor
    // Ini dipanggil saat: new Product("A001", "Pupuk Cair")
    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // 3. Getters (Method untuk mengambil data)
    // Ini dipanggil saat: p.getId()
    public String getId() {
        return id;
    }

    // Ini dipanggil saat: p.getName()
    public String getName() {
        return name;
    }

    // 4. Setters (Opsional, jika nanti butuh mengubah data)
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
4. package com.upb.agripos.view;

public class ConsoleView {
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public void displayProduct(String id, String name) {
        System.out.println("Product ID: " + id);
        System.out.println("Product Name: " + name);
    }
}
5. package com.upb.agripos;

import com.upb.agripos.config.DatabaseConnection;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class AppMVC {
    public static void main(String[] args) {
        System.out.println("Hello, I am Khansa Amanda Icha Sentana - 240202838 (Week 10)");
        
        // Singleton Test
        DatabaseConnection.getInstance().connect();

        // MVC Integration
        Product myProduct = new Product("P001", "Benih Jagung Hibrida");
        ConsoleView myView = new ConsoleView();
        ProductController myController = new ProductController(myProduct, myView);

        myController.updateView();
    }
}
6. package com.upb.agripos;

public class ProductTest {
    
    @Test
    public void testProductGetters() {
        ProductTest p = new ProductTest();
        assertEquals("A001", p.getId(), "Kode produk harus sesuai");
        assertEquals("Pupuk Cair", p.getName(), "Nama produk harus sesuai");
    }

    private void assertEquals(String string, Object name, String string2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
    }

    private Object getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    private Object getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }
}

---

## Hasil Eksekusi
![alt text](<Screenshot (71).png>)
---

## Analisis
1. Cara kerja kode: Program dijalankan dari AppMVC, membuat koneksi Singleton, lalu menghubungkan Model, View, dan Controller untuk menampilkan data ke console.

2. Perbedaan dengan minggu sebelumnya: Minggu ini menggunakan design pattern MVC dan Singleton, sedangkan sebelumnya kode masih sederhana dan belum terstruktur.

3. Kendala & solusi: Kesalahan package dan pemanggilan class, diatasi dengan memperbaiki struktur folder dan import class.
---

## Kesimpulan
Dengan menerapkan design pattern MVC dan Singleton, program menjadi lebih terstruktur, mudah dipahami, serta lebih mudah dikembangkan dan dipelihara karena pemisahan tugas yang jelas pada setiap komponen.
---

## Quiz
1. Mengapa constructor pada Singleton harus bersifat private?
Constructor pada Singleton harus bersifat private agar objek tidak dapat dibuat secara langsung dari luar class, sehingga memastikan hanya ada satu instance yang digunakan di seluruh program.

2. Jelaskan manfaat pemisahan Model, View, dan Controller.
Manfaat pemisahan Model, View, dan Controller (MVC) adalah membuat kode lebih terstruktur, memudahkan pemeliharaan, pengujian, dan pengembangan karena setiap bagian memiliki tanggung jawab yang jelas.

3. Apa peran unit testing dalam menjaga kualitas perangkat lunak?
Peran unit testing adalah memastikan setiap fungsi atau komponen program berjalan sesuai harapan, mendeteksi kesalahan sejak dini, dan menjaga kualitas perangkat lunak saat dilakukan perubahan kode.

4. Apa risiko jika Singleton tidak diimplementasikan dengan benar?
Risiko jika Singleton tidak diimplementasikan dengan benar adalah munculnya lebih dari satu instance, pemborosan sumber daya, serta potensi konflik data atau perilaku program yang tidak konsisten.