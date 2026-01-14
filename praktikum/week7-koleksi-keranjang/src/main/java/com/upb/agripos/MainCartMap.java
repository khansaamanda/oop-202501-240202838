package main.java.com.upb.agripos;

public class MainCartMap {
    public static void main(String[] args) {
        System.out.println("Hello, I am Khansa Amanda Icha Sentana - 240202838 (Week 7)");

        Product p1 = new Product("P01", "Beras", 50000, 2);
        Product p2 = new Product("P02", "Pupuk", 30000, 5);

        ShoppingCartMap cart = new ShoppingCartMap();
        cart.addProduct(p1);
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.printCart();

        cart.removeProduct(p1);
        cart.printCart();
    }
}
