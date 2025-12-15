# Laporan Praktikum Minggu 5 (sesuaikan minggu ke berapa?)
Topik: Abstraction dan Interface pada Sistem Pembayaran (Agri-POS)

## Identitas
- Nama  : Khansa Amanda Icha Sentana
- NIM   : 240202838
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa memahami konsep abstraction dan interface dalam pemrograman berorientasi objek (OOP) serta mampu mengimplementasikannya pada studi kasus sistem pembayaran dengan membuat class turunan (Cash, E-Wallet) yang mengoverride method abstrak dan mengimplementasikan interface secara benar.

---

## Dasar Teori
1. Abstraction digunakan untuk menyederhanakan sistem dengan menampilkan fungsi penting dan menyembunyikan detail implementasi.
2. Abstract class dapat memiliki method abstrak dan non-abstrak yang harus diimplementasikan oleh class turunannya.
3. Interface berisi kumpulan method abstrak yang digunakan sebagai kontrak perilaku suatu class.

---

## Langkah Praktikum
1. Setup & Coding
a. Membuat project Java dan struktur package.
b. Mengimplementasikan abstract class Pembayaran dan interface Receiptable, Validatable.
c. Membuat class Cash dan EWallet.
2. Run & Testing
Menjalankan MainAbstraction untuk menguji proses pembayaran dan cetak struk.
3. File yang Dibuat
a. Pembayaran.java, Cash.java, EWallet.java
b. Receiptable.java, Validatable.java, MainAbstraction.java
4. Commit Message
feat: abstraction & interface payment system

---

## Kode Program
1. package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp;

    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return getTotal() * 0.015; // fee 1.5%
    }

    @Override
    public boolean validasi() {
        return otp != null && otp.length() == 6;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public double totalBayar() {
        return getTotal() + biaya();
    }

    @Override
    public String cetakStruk() {
        return String.format("E-WALLET | INVOICE %s | TOTAL+FEE: %.2f | AKUN: %s | STATUS: %s | OTP Valid: %s",
                getInvoiceNo(), totalBayar(), akun,
                prosesPembayaran() ? "BERHASIL" : "GAGAL",
                validasi());
    }
}

2. package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\n--- Credit ---");
        System.out.println("Praktikum Week 5: Abstraction & Interface");
        System.out.println("NIM: " + nim);
        System.out.println("Nama: " + nama);
        System.out.println("--------------");
    }

    // Add no-arg overload to keep API consistent across weeks
    public static void print() {
        System.out.println("\nCredit By: 240202828 - Akhmad Akbar syarifudin");
    }
}

3. package com.upb.agripos;

import com.upb.agripos.model.kontrak.Receiptable;
import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.pembayaran.Cash;
import com.upb.agripos.model.pembayaran.EWallet;
import com.upb.agripos.model.pembayaran.Pembayaran;
import com.upb.agripos.util.CreditBy;


public class MainAbstraction {
    public static void main(String[] args) {
        // Kasus 1: Pembayaran Tunai (Cash) - Uang Cukup
        Pembayaran cashOK = new Cash("TRO-023", 145700, 150000);
        
        // Kasus 2: Pembayaran E-Wallet - Validasi Gagal (OTP kurang dari 6 digit)
        Pembayaran ewGagal = new EWallet("IRB-003", 150000, "ahmad@ewallet", "123");
        
        // Kasus 3: Pembayaran E-Wallet - Validasi Berhasil
        Pembayaran ewSukses = new EWallet("TBK-004", 20000, "syarif@ewallet", "123456");
        
        // DEMO 1: Proses Pembayaran
        System.out.println("--- Proses Pembayaran ---");
        System.out.println("CASH (Status): " + cashOK.prosesPembayaran());
        System.out.println("E-Wallet (Gagal Status): " + ewGagal.prosesPembayaran());
        System.out.println("E-Wallet (Sukses Status): " + ewSukses.prosesPembayaran());
        System.out.println("-------------------------");
        
        // DEMO 2: Dynamic Binding melalui Interface (Receiptable)
        System.out.println("--- Cetak Struk ---");
        
        // Casting diperlukan karena cashOK dan ewSukses bertipe Pembayaran
        // dan kita ingin memanggil method dari interface Receiptable
        System.out.println(((Receiptable) cashOK).cetakStruk());
        System.out.println(((Receiptable) ewSukses).cetakStruk());
        System.out.println(((Receiptable) ewGagal).cetakStruk());

        // DEMO 3: Menggunakan Validatable (khusus EWallet)
        if (ewSukses instanceof Validatable) {
            Validatable v = (Validatable) ewSukses;
            System.out.println("\nValidasi OTP E-Wallet Sukses: " + v.validasi());
        }

        CreditBy.print("240202838", "khansa amanda icha sentana");
    }
}

4. 
package com.upb.agripos.model.pembayaran;

public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    public abstract double biaya();
    public abstract boolean prosesPembayaran();

    public double totalBayar() {
        return total + biaya();
    }

    public String getInvoiceNo() { return invoiceNo; }
    public double getTotal() { return total; }
}

5. package com.upb.agripos.model.kontrak;

public interface Receiptable {
    String cetakStruk();
}

---
## Screenshot
![alt text](<Screenshot (1).png>)

---

## Analisis
1. Cara Kerja Kode:
Pembayaran sebagai abstract class, Cash dan EWallet mengimplementasikan method sesuai jenis pembayaran dan dipanggil di MainAbstraction.

2. Perbedaan Minggu Ini:
Menggunakan abstraction & interface dengan kontrak method, bukan hanya inheritance seperti minggu sebelumnya.

3. Kendala & Solusi:
Error method belum diimplementasi → diselesaikan dengan mengoverride semua method abstrak dan interface.
---

## Kesimpulan
Dengan menerapkan abstraction dan interface, program sistem pembayaran menjadi lebih terstruktur, fleksibel, dan mudah dikembangkan karena setiap jenis pembayaran memiliki kontrak method yang jelas serta implementasi yang terpisah.

---

## Quiz
1. Jelaskan perbedaan konsep dan penggunaan abstract class dan interface.
Jawaban:Abstract class digunakan sebagai kelas dasar yang dapat memiliki atribut, constructor, serta method abstrak dan non-abstrak. Interface digunakan sebagai kontrak perilaku yang hanya berisi deklarasi method (dan konstanta), sehingga class yang mengimplementasikannya wajib menyediakan implementasi method tersebut.
2. Mengapa multiple inheritance lebih aman dilakukan dengan interface pada Java?
Jawaban: Karena interface tidak memiliki implementasi method (kecuali default), penggunaan multiple inheritance dengan interface menghindari konflik implementasi method (diamond problem) sehingga lebih aman dan terkontrol.
3. Pada contoh Agri-POS, bagian mana yang paling tepat menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya.
Jawaban: Pada Agri-POS, Pembayaran paling tepat menjadi abstract class karena memiliki atribut dan alur umum pembayaran. Sementara Receiptable dan Validatable paling tepat menjadi interface karena hanya mendefinisikan perilaku seperti cetak struk dan validasi yang dapat diterapkan pada berbagai jenis pembayaran.