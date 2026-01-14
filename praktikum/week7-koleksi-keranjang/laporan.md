# Laporan Praktikum Minggu 7
Topik: Penerapan Class, Object, dan Java Collection (List dan Map) pada Sistem Keranjang Belanja Sederhana

## Identitas
- Nama  : Khansa Amanda Icha Sentana
- NIM   : 240202838
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa mampu memahami dan menerapkan konsep class dan object dalam pemrograman Java, membuat class Product dan ShoppingCart dengan prinsip enkapsulasi, serta mengimplementasikan operasi dasar seperti menambah, menghapus, dan menampilkan isi keranjang belanja beserta perhitungan total harga.
---

## Dasar Teori
1. Class merupakan blueprint atau rancangan yang mendefinisikan atribut dan method dari suatu objek.

2. Object adalah hasil instansiasi dari class yang dapat digunakan untuk menjalankan method dan mengakses data.

3. Enkapsulasi digunakan untuk melindungi data dengan cara membatasi akses langsung ke atribut melalui modifier akses dan method getter/setter.

4. Collection (Map/List) digunakan untuk menyimpan dan mengelola kumpulan objek secara terstruktur.

5. Method berfungsi untuk mendefinisikan perilaku objek, seperti menambah, menghapus, dan menampilkan data.

---

## Langkah Praktikum
1. Setup dan Persiapan
Membuat struktur project Java di Visual Studio Code, mengatur package, serta memastikan JDK dan ekstensi Java telah terpasang dengan benar.

2. Proses Coding
Membuat class Product, ShoppingCart, dan MainCart.
Class Product digunakan untuk menyimpan data produk, ShoppingCart untuk mengelola koleksi produk, dan MainCart sebagai class utama untuk menjalankan program.

3. Menjalankan Program (Run)
Menjalankan program melalui terminal atau fitur Run di VS Code untuk memastikan fungsi penambahan, penghapusan, dan penampilan isi keranjang berjalan dengan baik.

4. File/Kode yang Dibuat
a. Product.java
b. ShoppingCart.java
c. MainCart.java

5. Commit Message
Menambahkan implementasi class Product dan ShoppingCart untuk simulasi keranjang belanja

---

## Kode Program
1. package main.java.com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am Khansa Amanda Icha Sentana - 240202838 (Week7)");

        Product p1 = new Product("P01", "Beras", 50000);
        Product p2 = new Product("P02", "Pupuk", 30000);

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.printCart();

        cart.removeProduct(p1);
        cart.printCart();
    }
}
2. package main.java.com.upb.agripos;

public class Product {
    private final String code;
    private final String name;
    private final double price;

    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}
3. package main.java.com.upb.agripos;

import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> items = new ArrayList<>();

    public void addProduct(Product p) { items.add(p); }
    public void removeProduct(Product p) { items.remove(p); }

    public double getTotal() {
        double sum = 0;
        for (Product p : items) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void printCart() {
        System.out.println("Isi Keranjang:");
        for (Product p : items) {
            System.out.println("- " + p.getCode() + " " + p.getName() + " = " + p.getPrice());
        }
        System.out.println("Total: " + getTotal());
    }
}
4. package main.java.com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartMap {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p) { items.put(p, items.getOrDefault(p, 0) + 1); }

    public void removeProduct(Product p) {
        if (!items.containsKey(p)) return;
        int qty = items.get(p);
        if (qty > 1) items.put(p, qty - 1);
        else items.remove(p);
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("Isi Keranjang (Map):");
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            System.out.println("- " + e.getKey().getCode() + " " + e.getKey().getName() + " x" + e.getValue());
        }
        System.out.println("Total: " + getTotal());
    }
}
---

## Hasil Eksekusi
![alt text](<Screenshot (34).png>)
---

## Analisis
1. Cara Berjalannya Kode
Program bekerja dengan alur Manajemen Koleksi:
a. Membuat objek produk (Beras & Pupuk).
b. Memasukkan produk ke dalam objek ShoppingCart.
c. Menghitung total belanja secara otomatis melalui perulangan (looping).
d. Melakukan penghapusan produk secara dinamis, di mana daftar belanja langsung terupdate saat dicetak kembali.

2. Perbedaan Pendekatan
a. Dinamis vs Statis: Minggu ini menggunakan Java Collections (ArrayList atau HashMap), sehingga jumlah barang bisa ditambah/dikurang kapan saja. Minggu sebelumnya kemungkinan masih menggunakan variabel tunggal atau Array biasa yang ukurannya kaku.
b. Manajemen Data: Fokus pada cara mengelola sekumpulan objek di dalam satu wadah (container).

3. Kendala dan Solusi
a. Kendala: Muncul error tipe data (seperti yang terlihat di kolom chat VS Code) karena mencoba mengalikan teks (String) dengan angka (Integer).

b. Solusi: Melakukan konversi tipe data (parsing) atau memastikan variabel harga menggunakan tipe numerik (double/int) agar bisa dihitung secara matematis.
---

## Kesimpulan
Penggunaan Collection dan Object membuat pengelolaan data (seperti menambah dan menghapus item) menjadi lebih dinamis, terstruktur, dan mudah dikembangkan dibandingkan menggunakan array biasa.

---

## Quiz
1. Jelaskan perbedaan mendasar antara List, Map, dan Set.
jawab:
a. List: Data berurutan, boleh duplikat.
b. Set: Tidak boleh ada data duplikat.
c. Map: Data berupa pasangan key–value, key tidak boleh duplikat.

2. Mengapa ArrayList cocok digunakan untuk keranjang belanja sederhana?
jawab: 
a. Mudah digunakan
b. Data berurutan
c. Boleh menyimpan item yang sama
d. Efisien untuk tambah dan akses data

3. Bagaimana struktur Set mencegah duplikasi data?
jawab:
Set mengecek kesamaan data (equals() dan hashCode()), sehingga data yang sama tidak bisa ditambahkan dua kali.

4. Kapan sebaiknya menggunakan Map dibandingkan List? Jelaskan dengan contoh.
jawab:
Gunakan Map saat data memiliki pasangan key–value.
Contoh:
Produk (key) → Jumlah (value) dalam keranjang belanja.
