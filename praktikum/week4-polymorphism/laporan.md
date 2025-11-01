# Laporan Praktikum Minggu 4
Topik: Penerapan Polymorphism pada Class Produk dan Turunannya

## Identitas
- Nama  : Khansa Amanda Icha Sentana
- NIM   : 240202838
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa memahami konsep polymorphism dalam pemrograman berorientasi objek serta dapat mengimplementasikan berbagai bentuk objek turunan (Benih, Pupuk, AlatPertanian, dan ObatHama) dari class induk Produk, sehingga masing-masing objek dapat dipanggil melalui referensi superclass dengan perilaku (method) yang berbeda-beda sesuai tipenya.

---

## Dasar Teori
1. Polymorphism adalah konsep dalam OOP yang memungkinkan satu referensi objek (misalnya dari superclass) digunakan untuk merujuk pada berbagai bentuk objek subclass yang berbeda.

2. Dengan polymorphism, metode yang sama dapat memiliki perilaku berbeda tergantung pada objek yang memanggilnya (melalui method overriding).

3. Method overriding terjadi ketika subclass mendefinisikan ulang metode dari superclass dengan implementasi yang berbeda untuk menyesuaikan kebutuhan spesifik.

4. Polymorphism mempermudah pengembangan dan pemeliharaan kode karena memungkinkan program menangani banyak tipe objek secara umum melalui referensi superclass.

5. Dalam Java, polymorphism biasanya diterapkan menggunakan pewarisan (inheritance) dan referensi bertipe superclass yang menunjuk ke objek subclass.

---

## Langkah Praktikum
1. Membuka proyek Java oop-202501-240202838 di Visual Studio Code.

2. Membuat folder week4-polymorphism dan file MainPolymorphism.java di dalamnya.

3. Membuat class Produk sebagai superclass dan class turunan (Benih, Pupuk, AlatPertanian, ObatHama) di paket model.

4. Menambahkan class CreditBy di paket util untuk menampilkan identitas mahasiswa.

5. Mengimplementasikan konsep polymorphism dengan memanggil objek subclass melalui referensi Produk.

6. Menjalankan program melalui terminal dengan perintah Run: MainPolymorphism.java dan memverifikasi hasil output.

7. Melakukan commit dengan pesan:
"Implementasi polymorphism pada class Produk dan turunannya"

---

## Kode Program
1. package com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String bahan;

    public AlatPertanian(String kode, String nama, double harga, int stok, String bahan) {
        super(kode, nama, harga, stok);
        this.bahan = bahan;
    }

    @Override
    public String getInfo() {
        return "Alat Pertanian: " + super.getInfo() + ", Bahan: " + bahan;
    }
}
2. package com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    @Override
    public String getInfo() {
        return "Benih: " + super.getInfo() + ", Varietas: " + varietas;
    }
}
3. package com.upb.agripos.model;

public class ObatHama extends Produk {
    private String bahanAktif;

    public ObatHama(String kode, String nama, double harga, int stok, String bahanAktif) {
        super(kode, nama, harga, stok);
        this.bahanAktif = bahanAktif;
    }

    @Override
public String getInfo() {
    return "Obat Hama: Produk: " + super.getInfo() + ", Bahan Aktif: " + bahanAktif;
}

}
4. package com.upb.agripos.model;

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

    // === Method Overloading ===
    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void tambahStok(double jumlah) {
        this.stok += (int) jumlah;
    }

    // === Getter methods (tambahan penting) ===
    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    // === Method Default (Overridable) ===
    public String getInfo() {
        return "Produk: " + nama + " (Kode: " + kode + ")";
    }

    public void kurangiStok(int i) {
         
        throw new UnsupportedOperationException("Unimplemented method 'kurangiStok'");
    }

    
}
5. package com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }


    @Override
    public String getInfo() {
        return "Pupuk: " + super.getInfo() + ", Jenis: " + jenis;
    }

    public String getJenis() {
        return jenis;
    }
}
6. package com.upb.agripos.util;

public class CreditBy {
    public static void print() {
        System.out.println("\nCredit By: 240202838 - Khansa Amanda Icha Sentana");
    }
}
7. package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainPolymorphism {
    public static void main(String[] args) {

        Produk[] daftarProduk = {
            new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
            new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja"),
            new ObatHama("ABADI-NANJAYA", "Obat pembasmi kutu daun", 100000, 20, "asamsulfate")
        };

        for (Produk p : daftarProduk) {
            System.out.println(p.getInfo());
        }

        CreditBy.print();
    }
}
---

## Hasil Eksekusi
![alt text](<Screenshot 2025-11-01 111528.png>)
---

## Analisis
1. Program menjalankan konsep polymorphism dengan membuat beberapa objek turunan dari class Produk (Benih, Pupuk, AlatPertanian, dan ObatHama). Semua objek tersebut disimpan dalam satu array bertipe Produk, kemudian dipanggil melalui metode yang sama namun menghasilkan output berbeda sesuai class masing-masing (method overriding).

2. Pendekatan minggu ini berbeda dengan minggu sebelumnya (inheritance) karena fokusnya bukan hanya pada pewarisan atribut dan metode, tetapi pada kemampuan objek untuk memiliki perilaku berbeda meskipun dipanggil melalui referensi yang sama.

3. Kendala yang dihadapi adalah memastikan setiap subclass memiliki implementasi metode tampilInfo() yang sesuai agar hasil output berbeda, serta memastikan package dan struktur folder sesuai agar program dapat dijalankan tanpa error. Kendala tersebut diatasi dengan pengecekan ulang struktur project dan penyesuaian nama class sesuai deklarasi.
---

## Kesimpulan
Dengan menerapkan polymorphism, program dapat memanggil berbagai objek turunan dari class Produk melalui referensi yang sama, namun tetap menampilkan perilaku berbeda sesuai class masing-masing. Konsep ini membuat kode lebih fleksibel, efisien, dan mudah dikembangkan.

---

## Quiz
Minggu Ke-4:
1. Apa perbedaan overloading dan overriding?
Jawaban:
Overloading terjadi ketika dua atau lebih metode memiliki nama sama tetapi parameter berbeda dalam satu class.
Sedangkan overriding terjadi ketika subclass menulis ulang metode dari superclass dengan isi atau perilaku yang berbeda.

2. Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding?
Jawaban:
Dalam dynamic binding, Java menentukan metode yang dipanggil saat program berjalan (runtime) berdasarkan objek sebenarnya yang dirujuk oleh referensi, bukan berdasarkan tipe referensinya.

3. Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.
Jawaban:
Contohnya pada sistem POS toko elektronik, class Produk bisa diturunkan menjadi Laptop, Smartphone, dan Televisi, di mana masing-masing memiliki metode tampilInfo() yang menampilkan detail berbeda sesuai jenis produk.