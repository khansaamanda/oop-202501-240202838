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