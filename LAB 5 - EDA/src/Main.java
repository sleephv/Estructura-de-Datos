// Simulacion del famoso juego Conecta 4    
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scoreboard Puntuaciones = new Scoreboard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a Conecta 4");
        System.out.print("Ingrese nombre del Jugador A: ");
        String jugadorA = scanner.nextLine();
        Puntuaciones.registerPlayer(jugadorA);
        System.out.print("Ingrese nombre del Jugador B: ");
        String jugadorB = scanner.nextLine();
        Puntuaciones.registerPlayer(jugadorB);
        boolean nuevoJuego = true;
        while (nuevoJuego) {
            Game game = new Game(jugadorA, jugadorB);
            String ganador = game.play();
            if (game.getStatus().equals(Game.Victoria)) {
                String perdedor = ganador.equals(jugadorA) ? jugadorB : jugadorA;
                Puntuaciones.addGameResult(ganador, perdedor, false);
            } else if (game.getStatus().equals(Game.Empate)) {
                Puntuaciones.addGameResult(jugadorA, jugadorB, true);
            }
            System.out.println("\n--- Resumen del Scoreboard ---");
            System.out.println("Partidas jugadas: " + Puntuaciones.getPlayedGames());
            System.out.println(jugadorA + " : Victorias: " + Puntuaciones.getPlayer(jugadorA).getWins() +
                    ", Empates: " + Puntuaciones.getPlayer(jugadorA).getDraws() +
                    ", Derrotas: " + Puntuaciones.getPlayer(jugadorA).getLosses());

            System.out.println(jugadorB + " : Victorias: " + Puntuaciones.getPlayer(jugadorB).getWins() +
                    ", Empates: " + Puntuaciones.getPlayer(jugadorB).getDraws() +
                    ", Derrotas: " + Puntuaciones.getPlayer(jugadorB).getLosses());
            System.out.print("¿Otra partida? (si/no): ");
            String resp = scanner.nextLine().trim().toLowerCase();
            nuevoJuego = resp.equals("si");
        }
        System.out.println("¡Gracias por jugar!");
    }
}