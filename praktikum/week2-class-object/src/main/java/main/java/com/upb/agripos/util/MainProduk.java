package main.java.com.upb.agripos.util;

import main.java.com.upb.agripos.model.Produk;


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

