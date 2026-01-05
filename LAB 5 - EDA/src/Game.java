import java.util.Scanner;
public class Game {
    public static final String En_Progreso = "En_Progreso";
    public static final String Victoria = "Victoria";
    public static final String Empate = "Empate";
    private String Estado;
    private String NombreDelGanador;
    private String NombreJugadorA;
    private String NombreJugadorB;
    private ConnectFour connectFour;
    public Game(String NombreJugadorA, String NombreJugadorB) {
        this.NombreJugadorA = NombreJugadorA;
        this.NombreJugadorB = NombreJugadorB;
        this.connectFour = new ConnectFour();
        this.Estado = En_Progreso;
        this.NombreDelGanador = "";
    }
    public String play() {
        Scanner scanner = new Scanner(System.in);
        while (Estado.equals(En_Progreso)) {
            printBoard();
            String JugadorActual = connectFour.getSimbolo() == 'X' ? NombreJugadorA : NombreJugadorB;
            System.out.print("Turno de " + JugadorActual + " (" + connectFour.getSimbolo() + "). Elige columna (0-6): ");
            int col;
            try {
                col = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intenta de nuevo.");
                scanner.nextLine();
                continue;
            }
            boolean moveMade = connectFour.makeMove(col);
            if (!moveMade) {
                System.out.println("Movimiento inválido (columna llena o fuera de rango). Intenta de nuevo.");
                continue;
            }
            char resultado = connectFour.isGameOver();
            if (resultado == 'X' || resultado == 'O') {
                Estado = Victoria;
                NombreDelGanador = (resultado == 'X') ? NombreJugadorA : NombreJugadorB;
                printBoard();
                System.out.println("¡Victoria de " + NombreDelGanador + "!");
                return NombreDelGanador;
            } else if (resultado == 'E') {
                Estado = Empate;
                NombreDelGanador = "";
                printBoard();
                System.out.println("¡El juego termina en empate!");
                return "";
            }
        }
        return "";
    }
    private void printBoard() {
        char[][] Tablero = connectFour.getTablero();
        System.out.println("Estado actual del tablero:");
        for (int fila = 0; fila < 6; fila++) {
            System.out.print("|");
            for (int col = 0; col < 7; col++) {
                System.out.print(Tablero[fila][col]);
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println(" 0 1 2 3 4 5 6\n");
    }
    public String getStatus() {
        return Estado;
    }
    public String getWinnerPlayerName() {
        return NombreDelGanador;
    }
    public String getPlayerNameA() {
        return NombreJugadorA;
    }
    public String getPlayerNameB() {
        return NombreJugadorB;
    }
    public ConnectFour getConnectFour() {
        return connectFour;
    }
}