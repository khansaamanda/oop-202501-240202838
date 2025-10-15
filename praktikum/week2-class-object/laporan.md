# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: [Tuliskan judul topik, misalnya "Class dan Object"]

## Identitas
- Nama  : Khansa Amanda Icha Sentana
- NIM   : 240202838
- Kelas : 3IKRA


---

## Tujuan
(Tuliskan tujuan praktikum minggu ini.  
Contoh: *Mahasiswa memahami konsep class dan object serta dapat membuat class Produk dengan enkapsulasi.*)

---

## Dasar Teori
1. Class adalah blueprint atau template yang digunakan untuk membuat objek dan mendefinisikan atribut serta perilaku (metode) dari objek tersebut.

2. Object merupakan instansiasi dari class yang memiliki data (atribut) dan dapat melakukan aksi (metode) sesuai class-nya.

3. Enkapsulasi digunakan untuk membungkus data agar tidak diakses langsung dari luar class, melainkan melalui metode (getter dan setter).

4. Method adalah fungsi di dalam class yang digunakan untuk mengatur atau memproses data objek.

5. Instansiasi dan pemanggilan method memungkinkan interaksi antar objek, seperti penambahan stok dan pengurangan stok produk pada contoh program.

---

## Langkah Praktikum
1. Langkah-langkah yang dilakukan

a. Membuka project Java di VS Code pada folder week2-class-object.

b. Membuat class Produk, CreditBy, dan MainProduk untuk mengimplementasikan konsep class dan object.

c. Melakukan coding pada masing-masing class:

Produk.java berisi atribut dan metode untuk pengelolaan stok produk.

CreditBy.java berisi metode untuk menampilkan identitas mahasiswa.

MainProduk.java digunakan untuk menjalankan program utama (main method).

d. Menjalankan program menggunakan perintah Run: MainProduk di terminal.

e. Melihat hasil transaksi masuk dan keluar pada terminal.

2. File/kode yang dibuat

a. Produk.java

b. CreditBy.java

c. MainProduk.java

3. Commit message yang digunakan

a. Add class and object implementation

b. Update transaksi masuk dan keluar

c. Add CreditBy print method

---

## Kode Program
1. package main.java.com.upb.agripos.model;

public class Produk {
     private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

     public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

     public void tambahStok(int jumlah) {
        this.stok += jumlah;
        System.out.println("\n===TRANSAKSI MASUK===");
        System.out.println("Produk : " + nama + ", " + kode);
        System.out.println("Jumlah : +" + jumlah);
        System.out.println("Stok Sekarang : " + stok);
        System.out.println("---------------------------");
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
            System.out.println("\n===TRANSAKSI KELUAR===");
            System.out.println("Produk : " + nama + ", " + kode);
            System.out.println("Jumlah : -" + jumlah);
            System.out.println("Stok Sekarang : " + stok);
            System.out.println("---------------------------");
        } else {
            System.out.println("\nStok " + nama + ", " + kode + " tidak mencukupi!");
            System.out.println("Stok tersedia : " + stok + " | Diminta : " + jumlah);
            System.out.println("---------------------------");
        }
    }
}
2. package main.java.com.upb.agripos.util;
public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
3. package main.java.com.upb.agripos.util;

import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.util.CreditBy;

public class MainProduk {
     public static void main(String[] args) {
        Produk p1 = new Produk("BNH-001", "Benih kacang IR64", 15000, 100);
        Produk p2 = new Produk("PPK-101", "Pupuk padi 50kg", 450000, 40);
        Produk p3 = new Produk("VTM-501", "vitamin cabe", 100000, 15);

        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());
  // Transaksi penambahan stok
        p1.tambahStok(25);  // stok datang dari gudang

        // Transaksi pengurangan stok
        p2.kurangiStok(20); // produk dibeli pelanggan
      
        // Tampilkan identitas mahasiswa
        CreditBy.print("240202838", "Khansa Amanda Icha Sentana");
    }
}
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](/praktikum/week2-class-object/screenshots/Class%20and%20Objek.png)
)
---

## Analisis
1. Bagaimana kode berjalan

a. Program terdiri dari beberapa class: Produk, CreditBy, dan MainProduk.

b. Di dalam MainProduk.main(), beberapa objek Produk dibuat (misalnya p1, p2, p3).

c. Program menampilkan daftar produk beserta kode, nama, harga, dan stok awal.

d. Lalu dilakukan transaksi masuk menggunakan method seperti tambahStok(jumlah:25) untuk menambah stok.

e. Dilanjutkan transaksi keluar menggunakan kurangiStok(jumlah:20) untuk mengurangi stok saat produk dibeli pelanggan.

f. Setelah itu, identitas mahasiswa ditampilkan dengan pemanggilan CreditBy.print(nim, nama).

g. Output di terminal menunjukkan perubahan stok sebelum dan sesudah transaksi, serta identitas pembuat program di bagian akhir.

2. Perbedaan pendekatan minggu ini dibanding minggu sebelumnya

a. Minggu sebelumnya: fokus pada pemrograman prosedural, di mana kode berjalan berurutan dalam satu fungsi main(), tanpa pemisahan konsep data dan perilaku.

b. Minggu ini: sudah menggunakan Object Oriented Programming (OOP).

Ada class (Produk) yang menjadi blueprint dari objek.

Ada object nyata (misalnya p1, p2, p3) yang dibuat dari class tersebut.

Setiap objek punya atribut (kode, nama, harga, stok) dan method (misalnya tambahStok() dan kurangiStok()).

Program jadi lebih modular, mudah dikembangkan, dan terstruktur.

3. Kendala yang dihadapi dan cara mengatasinya
Selama praktikum, beberapa kendala yang muncul antara lain error cannot find symbol karena nama class atau file belum sesuai, serta output tidak muncul akibat kesalahan jalur file atau method main(). Selain itu, sempat terjadi kesalahan logika pada perubahan stok dan masalah import antar package. Semua kendala tersebut dapat diatasi dengan memastikan struktur file benar, melakukan kompilasi ulang, serta memperbaiki logika dan penulisan kode sesuai aturan OOP.
---

## Kesimpulan
Dengan menggunakan konsep class dan object, program menjadi lebih terstruktur, modular, dan mudah dikembangkan. Setiap data dan fungsinya dapat dikelola melalui objek, sehingga proses seperti transaksi stok dan identitas mahasiswa dapat dijalankan secara efisien dan terorganisir.
---

## Quiz
1. Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?
Atribut sebaiknya dideklarasikan sebagai private untuk menjaga keamanan data (data hiding). Dengan demikian, atribut tidak bisa diakses atau diubah langsung dari luar class, sehingga mencegah perubahan nilai yang tidak diinginkan dan menjaga konsistensi data di dalam objek.

2. Apa fungsi getter dan setter dalam enkapsulasi?
Getter dan setter berfungsi sebagai pengendali akses terhadap atribut private. Getter digunakan untuk mengambil (membaca) nilai atribut, sedangkan setter digunakan untuk mengubah (menulis) nilai atribut dengan cara yang terkontrol, misalnya dengan menambahkan validasi sebelum perubahan dilakukan.

3. Bagaimana cara class Produk mendukung pengembangan aplikasi POS yang lebih kompleks?
Class Produk dapat menjadi dasar bagi pengelolaan data barang pada sistem POS (Point of Sale). Dengan memiliki atribut seperti kode, nama, harga, dan stok serta method seperti tambahStok() dan kurangiStok(), class ini dapat dikembangkan lebih lanjut untuk menambah fitur seperti pencatatan transaksi, perhitungan total penjualan, hingga integrasi dengan database dan laporan keuangan. 