package com.upb.agripos.model;

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