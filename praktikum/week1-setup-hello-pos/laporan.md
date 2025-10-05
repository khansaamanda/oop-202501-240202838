# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: [Tuliskan judul topik, misalnya "Class dan Object"]

## Identitas
- Nama  : [Khansa Amanda Icha Sentana]
- NIM   : [240202838]
- Kelas : [3IKRA]

---

## Tujuan
Mahasiswa memahami perbedaan paradigma pemrograman Procedural, Functional, dan Object Oriented Programming (OOP) dalam bahasa Java, serta dapat membuat program sederhana menggunakan ketiga paradigma tersebut untuk menampilkan informasi identitas diri.
---

## Dasar Teori
1. Pemrograman Prosedural adalah paradigma pemrograman yang menyusun kode dalam bentuk prosedur atau fungsi, di mana eksekusi program dilakukan langkah demi langkah.

2. Pemrograman Berorientasi Objek (OOP) menggunakan konsep class dan object untuk memodelkan dunia nyata, dengan prinsip utama seperti enkapsulasi, pewarisan (inheritance), dan polimorfisme.

3. Class adalah blueprint atau cetak biru dari objek, sedangkan object merupakan instansiasi dari class tersebut.

4. Pemrograman Fungsional menekankan pada penggunaan fungsi sebagai elemen utama, sering menggunakan konsep lambda expression dan functional interface seperti BiConsumer.

5. Ketiga paradigma ini (Prosedural, OOP, dan Fungsional) memiliki tujuan sama yaitu menyusun program agar lebih terstruktur, mudah dipahami, dan mudah dikembangkan.

---

## Langkah Praktikum
1. Langkah-langkah yang dilakukan

a.) Melakukan setup project Java di VS Code dengan nama OOP-202501-240202838.

b.) Membuat folder praktikum/week1-setup-hello-pos/src untuk menyimpan file program.

c.) Membuat tiga file program Java untuk menerapkan tiga paradigma pemrograman:

helloProcedural.java → paradigma Prosedural

helofunctional.java → paradigma Fungsional

helloOOP.java → paradigma Berorientasi Objek (OOP)

d.) Menulis kode program sesuai paradigma masing-masing.

e.) Melakukan compile dan run menggunakan terminal VS Code untuk memastikan program berjalan dengan benar.

f.) Mengecek hasil output di terminal dan memastikan menampilkan pesan:
Hello World I am Khansa Amanda Icha Sentana_240202838

g.) Menambahkan pesan akhir “Program Selesai Dijalankan, Terimakasih”.

2. File/kode yang dibuat

a.) helloProcedural.java → menggunakan pendekatan prosedural, deklarasi variabel dan output di dalam main().

b.) helofunctional.java → menggunakan lambda expression dan interface BiConsumer untuk fungsi sapa.

c.) helloOOP.java → membuat class Mahasiswa dan memanggil metode sapa() melalui objek.

3. Commit message yang digunakan

a.) add: helloProcedural.java for procedural programming

b.) add: helofunctional.java for functional programming

c.) add: helloOOP.java for object-oriented programming

d.) update: add output message and test run result

---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

```java
import java.util.function.BiConsumer;

public class helofunctional {

     public static void main(String[] args) {
        // Membuat fungsi "sapa" menggunakan lambda
        BiConsumer<String, String> sapa = 
            (nama, nim) -> System.out.println("Hello World, I am " + nama + " -" + nim);

        // eksekusi fungsi
        sapa.accept("Khansa Amanda Icha Sentana", "240202838");
        System.out.println("Program Functional Selesai, Terimakasih");
}
}
class Mahasiswa{
    String nama;
    int nim;

    Mahasiswa(String n, int m){
        this.nama = n;
        this.nim = m;
    }
    void sapa(){
    System.out.println("Hello World I am " +nama + "_" + nim );
}
}
public class helloOOP {
    public static void main(String[] args) {
         //membuat variable scanner input
       Mahasiswa a = new Mahasiswa("Khansa Amanda Icha Sentana", 240202838);
        // Meminta Input Dari User
        // Mengambil Object dari scaner input
      
        // Call method sapa
        a.sapa();
        

        System.out.println("Program OOP Selesai Dijalankan, Terimakasih");

    }
    
}
public class helloProcedural {

  
    public static void main(String[] args) {
         String nama = "Khansa Amanda Icha Sentana";
         String nim = "240202838";

         System.out.println("Hello World I'm "+ nama + " -"+ nim);

       

    }

    
}
```
)
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots/hasil.png)
)
---

## Analisis
1. Perbedaan dari Minggu Sebelumnya (Prosedural)

Pendekatan sebelumnya bersifat prosedural, program berjalan berurutan lewat main, dengan data dan logika terpisah serta belum terstruktur.

2. Pendekatan Baru: OOP dan Fungsional

a.) OOP: Data dan perilaku digabung dalam kelas Mahasiswa. Objek dibuat dan menjalankan fungsinya sendiri (mahasiswa.sapa()), lebih terstruktur dan modular.

b.) Fungsional: Menggunakan Lambda Expression dan Functional Interface untuk logika sapa, membuat kode lebih ringkas dan mudah diuji.

3. Cara Kode Berjalan & Kendala

OOP: Tantangan memahami konsep kelas dan objek.
Fungsional: Sulit di awal karena sintaks Lambda.
Solusi: Gunakan fitur IDE dan pahami tiap paradigma sebagai alat pemecahan masalah berbeda.
---

## Kesimpulan
Praktikum minggu ini terjadi pergeseran sukses dari Pemrograman Prosedural menuju dua paradigma yang lebih canggih, yaitu OOP dan Fungsional. Pemrograman Berorientasi Objek (OOP), melalui penggunaan Class dan Object, menjadikan kode lebih terstruktur dan mudah dikembangkan karena menggabungkan data dan perilaku terkait. Sementara itu, Pemrograman Fungsional, dengan Lambda Expression, memungkinkan penulisan kode yang ringkas dan elegan dengan memisahkan logika dari eksekusi. Secara keseluruhan, penguasaan ketiga paradigma ini membuktikan pentingnya fleksibilitas dalam memilih alat yang tepat untuk mengelola dan memecahkan masalah kompleks dalam pengembangan perangkat lunak.
---

## Quiz
(1. 1.  Apakah OOP selalu lebih baik dari prosedural? 
OOP tidak selalu lebih baik dari prosedural.
OOP cocok untuk sistem besar dan kompleks, sedangkan prosedural lebih efisien untuk program kecil dan sederhana.
2. Kapan functional programming lebih cocok digunakan
 dibanding OOP atau prosedural?
Functional programming cocok untuk komputasi data, pemrosesan paralel, dan sistem yang perlu mudah diuji tanpa efek samping.
3. Bagaimana paradigma (prosedural, OOP, fungsional) 
memengaruhi maintainability dan scalability aplikasi?
Pengaruh paradigma:
a.) Prosedural: sulit dirawat saat program besar.
b.) OOP: mudah diperluas dan dipelihara.
c.) Fungsional: kode ringkas, mudah diuji, dan mendukung paralelisme. 
4. MengapaOOP lebih cocok untuk mengembangkan
 aplikasi POS dibanding prosedural?
OOP cocok untuk aplikasi POS karena dapat memodelkan objek nyata (produk, transaksi, kasir) dan memudahkan pengembangan fitur.
5. Bagaimana paradigma fungsional dapat membantu
 mengurangi kode berulang (boilerplate code)?
Functional programming mengurangi kode berulang lewat fungsi murni dan higher-order functions seperti map, filter, dan reduce. 

2. [Tuliskan kembali pertanyaan 2 dari panduan]  
   **Jawaban:** …  

3. [Tuliskan kembali pertanyaan 3 dari panduan]  
   **Jawaban:** …  )
