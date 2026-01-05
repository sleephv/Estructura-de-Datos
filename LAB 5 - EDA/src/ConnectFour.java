public class ConnectFour {
    private char[][] Tablero;
    private char Simbolo;
    public ConnectFour() {
        Tablero = new char[6][7];
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 7; columna++) {
                Tablero[fila][columna] = ' ';
            }
        }
        Simbolo = 'X';
    }
    public boolean makeMove(int columna) {
        if (columna < 0 || columna >= 7) return false;
        for (int fila = 5; fila >= 0; fila--) {
            if (Tablero[fila][columna] == ' ') {
                Tablero[fila][columna] = Simbolo;
                Simbolo = (Simbolo == 'X') ? 'O' : 'X';
                return true;
            }
        }
        return false;
    }
    public char isGameOver() {
        for (int fila = 0; fila < 6; fila++) {
            for (int col = 0; col <= 3; col++) {
                char c = Tablero[fila][col];
                if (c != ' ' && c == Tablero[fila][col+1] && c == Tablero[fila][col+2] && c == Tablero[fila][col+3])
                    return c;
            }
        }
        for (int col = 0; col < 7; col++) {
            for (int fila = 0; fila <= 2; fila++) {
                char c = Tablero[fila][col];
                if (c != ' ' && c == Tablero[fila+1][col] && c == Tablero[fila+2][col] && c == Tablero[fila+3][col])
                    return c;
            }
        }
        for (int fila = 0; fila <= 2; fila++) {
            for (int col = 0; col <= 3; col++) {
                char c = Tablero[fila][col];
                if (c != ' ' && c == Tablero[fila+1][col+1] && c == Tablero[fila+2][col+2] && c == Tablero[fila+3][col+3])
                    return c;
            }
        }
        for (int fila = 3; fila < 6; fila++) {
            for (int col = 0; col <= 3; col++) {
                char c = Tablero[fila][col];
                if (c != ' ' && c == Tablero[fila-1][col+1] && c == Tablero[fila-2][col+2] && c == Tablero[fila-3][col+3])
                    return c;
            }
        }
        boolean lleno = true;
        for (int fila = 0; fila < 6; fila++) {
            for (int col = 0; col < 7; col++) {
                if (Tablero[fila][col] == ' ') {
                    lleno = false;
                    break;
                }
            }
        }
        if (lleno) return 'E';
        return ' ';
    }
    public char[][] getTablero() {return Tablero;}
    public char getSimbolo() {return Simbolo;}
}