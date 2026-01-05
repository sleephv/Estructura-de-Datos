import java.util.*;
public class Scoreboard {
    private TreeMap<Integer, List<String>> winTree;
    private HashMap<String, Player> Jugadores;
    private int partidasJugadas;
    public Scoreboard() {
        winTree = new TreeMap<>();
        Jugadores = new HashMap<>();
        partidasJugadas = 0;
    }
    public void registerPlayer(String nombreJugador) {
        if (!Jugadores.containsKey(nombreJugador)) {
            Player jugador = new Player(nombreJugador, 0, 0, 0);
            Jugadores.put(nombreJugador, jugador);
            winTree.computeIfAbsent(0, k -> new ArrayList<>()).add(nombreJugador);
        }
    }
    public boolean checkPlayer(String nombreJugador) {
        return Jugadores.containsKey(nombreJugador);
    }
    public void addGameResult(String nombreGanador, String nombrePerdedor, boolean empate) {
        if (!Jugadores.containsKey(nombreGanador) || !Jugadores.containsKey(nombrePerdedor)) return;
        Player ganador = Jugadores.get(nombreGanador);
        Player perdedor = Jugadores.get(nombrePerdedor);
        winTree.get(ganador.getWins()).remove(nombreGanador);
        winTree.get(perdedor.getWins()).remove(nombrePerdedor);
        if(empate) {
            ganador.addDraw();
            perdedor.addDraw();
        } else {
            ganador.addWin();
            perdedor.addLoss();
        }
        partidasJugadas++;
        winTree.computeIfAbsent(ganador.getWins(), k -> new ArrayList<>()).add(nombreGanador);
        winTree.computeIfAbsent(perdedor.getWins(), k -> new ArrayList<>()).add(nombrePerdedor);
    }
    public List<Player> winRange(int lo, int hi) {
        List<Player> resultado = new ArrayList<>();
        for (int victorias : winTree.subMap(lo, true, hi, true).keySet()) {
            for (String nombre : winTree.get(victorias)) {
                resultado.add(Jugadores.get(nombre));
            }
        }
        return resultado;
    }
    public List<Player> winSuccessor(int victorias) {
        Integer successorKey = winTree.higherKey(victorias);
        List<Player> resultado = new ArrayList<>();
        if (successorKey != null) {
            for (String nombre : winTree.get(successorKey)) {
                resultado.add(Jugadores.get(nombre));
            }
        }
        return resultado;
    }
    public int getPlayedGames() {return partidasJugadas;}
    public Player getPlayer(String nombreJugador) {
        return Jugadores.get(nombreJugador);
    }
}