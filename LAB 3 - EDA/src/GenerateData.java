// Simular un repositorio de Steam
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class GenerateData {
    private static final String[] nombres = {"Dragon", "Empire", "Quest", "Galaxy", "Legends", "Warrior"};
    private static final String[] categorias = {"Accion", "Aventura", "Estrategia", "RPG", "Deportes", "Simulacion"};
    private static final Random randomlock = new Random();
    public static ArrayList<Game> generateGames(int N) {
        ArrayList<Game> juegos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String nombre = nombres[randomlock.nextInt(nombres.length)] + nombres[randomlock.nextInt(nombres.length)];
            String categoria = categorias[randomlock.nextInt(categorias.length)];
            int precio = randomlock.nextInt(70001);
            int calidad = randomlock.nextInt(101);
            juegos.add(new Game(nombre, categoria, precio, calidad));
        }
        return juegos;
    }
    public static void GuardarArchivo(ArrayList<Game> juegos, String filename) {
        try (FileWriter prueba = new FileWriter(filename)) {
            prueba.write("Name,Category,Price,Quality\n");
            for (Game game : juegos) {
                prueba.write(game.getNombre() + "," + game.getCategoria() + "," + game.getPrecio() + "," + game.getCalidad() + "\n");
            }
        }
        catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        ArrayList<Game> dataset1 = generateGames(100);
        ArrayList<Game> dataset2 = generateGames(10000);
        ArrayList<Game> dataset3 = generateGames(1000000);
        GuardarArchivo(dataset1, "prueba 100.csv");
        GuardarArchivo(dataset2, "prueba 10000.csv");
        GuardarArchivo(dataset3, "prueba 1000000.csv");
        System.out.println("Archivos creados.");
    }
}