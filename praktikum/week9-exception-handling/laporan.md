# Laporan Praktikum Minggu 9
Topik: 

## Identitas
- Nama  : Khansa Amanda Icha Sentana
- NIM   : 240202838
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa diharapkan mampu memahami dan menerapkan mekanisme Exception Handling dalam pemrograman Java, dengan rincian:
1. Memahami konsep Try-Catch untuk menangani kesalahan (error) saat program berjalan (runtime).
2. Mampu membuat dan menggunakan Custom Exception (Exception buatan sendiri) seperti DuplicateProductException atau InsufficientStockException untuk kasus spesifik pada aplikasi Point of Sale (POS).
3. Menerapkan logika validasi bisnis (seperti pengecekan stok atau duplikasi produk) guna meningkatkan ketangguhan (robustness) aplikasi terhadap input yang tidak valid.

---

## Dasar Teori
1. Exception adalah sebuah peristiwa (event) yang terjadi selama eksekusi program yang mengganggu aliran normal instruksi (seperti kesalahan input atau kehabisan stok).

2. Try-Catch Block digunakan untuk memisahkan kode utama dari kode penanganan kesalahan; blok try berisi kode yang berisiko error, sementara catch menangani error tersebut agar program tidak berhenti tiba-tiba (crash).

3. Custom Exception adalah kelas pengecualian yang dibuat sendiri oleh programmer (dengan mewarisi kelas Exception atau RuntimeException) untuk menangani logika bisnis yang spesifik, seperti InsufficientStockException.

---

## Langkah Praktikum
1. Setup & Coding: * Membuat Custom Exception (DuplicateProductException, InsufficientStockException, dll).
a. Menambahkan logika throw pada method di class ShoppingCart.
b. Menyusun blok try-catch di MainExceptionDemo.java untuk menangani error.

2. File yang Dibuat: * MainExceptionDemo.java (Main class).
a. Product.java & ShoppingCart.java (Logika bisnis).
b. Berbagai file Exception (contoh: InvalidQuantityException.java).

3. Run: Mengeksekusi program di terminal VS Code dan memverifikasi pesan kesalahan muncul sesuai skenario (stok habis, duplikasi, dll).

4. Commit Message: feat: implement exception handling for POS system.

---

## Kode Program
1. package main.java.com.upb.agripos;

public class CartEmptyException extends Exception {
    public CartEmptyException(String msg) { super(msg); }
}
2. package main.java.com.upb.agripos;

public class DuplicateProductException extends Exception {
    public DuplicateProductException(String msg) { super(msg); }
}
3. package main.java.com.upb.agripos;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String msg) { super(msg); }
}
4. package main.java.com.upb.agripos;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String msg) { super(msg); }
}
5. package main.java.com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Hello, I am khansa amanda icha sentana (240202838) (Week9)");

        ShoppingCart cart = new ShoppingCart();
        Product p1 = new Product("P01", "Pupuk Organik", 25000, 70);

        // InvalidQuantityException
        try {
            cart.addProduct(p1, -1);
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // ProductNotFoundException
        try {
            cart.removeProduct(p1);
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // CartEmptyException
        try {
            cart.checkout();
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // DuplicateProductException
        try {
            cart.addProduct(p1, 1);
            cart.addProduct(p1, 1); // duplikat
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // InsufficientStockException
        try {
            ShoppingCart cart2 = new ShoppingCart();
            cart2.addProduct(p1, 5); // stok cuma 3
            cart2.checkout();
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }
    }
}
6. package main.java.com.upb.agripos;

public class Product {
    private final String code;
    private final String name;
    private final double price;
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
    public void reduceStock(int qty) { this.stock -= qty; }
}
7. package main.java.com.upb.agripos;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) { super(msg); }
}
8. package main.java.com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p, int qty)
            throws InvalidQuantityException, DuplicateProductException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                "Quantity harus lebih dari 0."
            );
        }

        if (items.containsKey(p)) {
            throw new DuplicateProductException(
                "Produk sudah ada di keranjang."
            );
        }

        items.put(p, qty);
    }

    public void removeProduct(Product p) throws ProductNotFoundException {
        if (!items.containsKey(p)) {
            throw new ProductNotFoundException("Produk tidak ada dalam keranjang.");
        }
        items.remove(p);
    }

    public void checkout()
            throws CartEmptyException, InsufficientStockException {

        if (items.isEmpty()) {
            throw new CartEmptyException(
                "Keranjang masih kosong."
            );
        }

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();

            if (product.getStock() < qty) {
                throw new InsufficientStockException(
                    "Stok tidak cukup untuk: " + product.getName()
                );
            }
        }

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("Isi Keranjang (Map):");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("- " + product.getCode() + " " + product.getName() + " = " + product.getPrice() + " x" + quantity);
        }
        System.out.println("Total: " + getTotalPrice());
    }
}
---

## Screenshot
![alt text](<Screenshot (38).png>)

## Analisis
1. Alur Berjalannya Kode
Program menjalankan simulasi transaksi pada sistem Agripos. Alurnya menggunakan Try-Catch untuk memvalidasi setiap tindakan:
a. Validasi: Saat ada aksi ilegal (stok habis, duplikat, atau keranjang kosong), program melempar (throw) Custom Exception.
b. Eksekusi: Pesan kesalahan ditangkap di blok catch dan ditampilkan ke terminal tanpa menghentikan program secara paksa (crash).

2. Perbedaan Pendekatan
a. Minggu Lalu: Masih menggunakan logika if-else konvensional untuk mengecek error, yang membuat kode bercampur antara logika bisnis dan validasi.
b. Minggu Ini: Menggunakan Exception Handling. Pemisahan kode menjadi lebih bersih, di mana logika utama ada di try dan penanganan masalah ada di catch.

3. Kendala dan Solusi
a. Kendala: Muncul error pada operator perkalian (*) karena mencoba mengalikan tipe data String dengan Integer (terlihat pada kolom chat AI di kanan bawah).
b. Solusi: Melakukan casting atau konversi tipe data String ke angka menggunakan Integer.parseInt() sebelum dilakukan operasi matematika.
c. Kendala Lain: Mengelola banyak file Custom Exception.
d. Solusi: Mengelompokkan semua class Exception ke dalam satu package yang sama agar mudah dipanggil oleh class utama.
---

## Kesimpulan
Dengan menerapkan Exception Handling dan Custom Exception, program menjadi lebih robust (tangguh) dan profesional karena mampu menangani berbagai skenario kesalahan input atau logika tanpa menghentikan paksa (crash) jalannya aplikasi.

Struktur kode kini lebih bersih dan terorganisir karena adanya pemisahan yang jelas antara alur logika bisnis utama (dalam blok try) dengan mekanisme penanganan kesalahan (dalam blok catch), sehingga memudahkan proses debugging dan pengembangan aplikasi ke depannya.

---

## Quiz
1. Jelaskan perbedaan error dan exception. 
jawab: 
Perbedaan error dan exception
a. Error: Kesalahan serius yang terjadi di luar kendali program dan tidak seharusnya ditangani, misalnya OutOfMemoryError.
b. Exception: Kesalahan yang terjadi saat program berjalan dan dapat ditangani, misalnya NullPointerException atau IOException.

2. Apa fungsi finally dalam blok try–catch–finally?
jawab: 
Fungsi finally dalam blok try–catch–finally
finally digunakan untuk menjalankan kode yang pasti dieksekusi, baik terjadi exception maupun tidak, seperti menutup file, koneksi database, atau resource lainnya.

3. Mengapa custom exception diperlukan? 
jawab:
a. Untuk mewakili kesalahan bisnis secara spesifik
b. Membuat kode lebih jelas, rapi, dan mudah dipahami
c. Memisahkan logika bisnis dari error teknis bawaan Java

4. Berikan contoh kasus bisnis dalam POS yang membutuhkan custom exception.
jawab: 
a. Stok barang habis saat transaksi → OutOfStockException
b. Jumlah pembayaran kurang dari total belanja → InsufficientPaymentException
c. Produk tidak terdaftar di sistem → ProductNotFoundException