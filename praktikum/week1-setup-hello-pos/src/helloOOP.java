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