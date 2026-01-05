class Player {
    String nombreJugador;
    int victorias;
    int empates;
    int derrotas;
    Player(String nombreJugador, int victorias, int empates, int derrotas) {
        this.nombreJugador = nombreJugador;
        this.victorias = victorias;
        this.empates = empates;
        this.derrotas = derrotas;
    }
    public int getWins() {return victorias;}
    public int getDraws() {return empates;}
    public int getLosses() {return derrotas;}
    public String getPlayerName() {return nombreJugador;}
    public void addWin() {
        victorias++;
    }
    public void addDraw() {
        empates++;
    }
    public void addLoss() {
        derrotas++;
    }
    public float winRate() {
        float juegosTotales = victorias + empates + derrotas;
        if (juegosTotales== 0) {
            return 0;
        }
        else {
            float porcentaje = victorias/juegosTotales;
            return porcentaje;
        }
    }
}