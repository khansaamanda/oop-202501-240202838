package com.upb.agripos;

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