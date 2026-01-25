package com.upb.agripos;

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