import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class UrnaElectoral {
    private LinkedList<Candidato> listaCandidatos;
    private Stack<Voto> historialVotos;
    private Queue<Voto> votosReportados;
    private int idCounter;
    public UrnaElectoral() {
        this.listaCandidatos = new LinkedList<>();
        this.historialVotos = new Stack<>();
        this.votosReportados = new LinkedList<>();
        this.idCounter = 1;
    }
    public Stack<Voto> getHistorialVotos() {
        return historialVotos;
    }
    public Queue<Voto> getVotosReportados() {
        return votosReportados;
    }
    public LinkedList <Candidato> getListaCandidatos() {
        return listaCandidatos;
    }
    public boolean verificarVotante(Votante votante){
        int id = votante.getID();
        for (Voto voto : historialVotos) {
            if (voto.getVotanteID() == id) {
                return true;
            }
        }
        return false;
    }
    public boolean registrarVoto(Votante votante, int candidatoID) {
        if(votante.getYaVoto()==true){
            System.out.println("Votante ya registrado");
            return false;
        }
        else{
            Voto nuevoVoto = new Voto(idCounter++, votante.getID(), candidatoID, obtenerTimestamp());
            for( Candidato candidato : listaCandidatos){
                if(candidato.getID() == candidatoID){
                    candidato.agregarVoto(nuevoVoto);
                    break;
                }
            }
            historialVotos.push(nuevoVoto);
            votante.marcarComoVotado();
            return true;
        }
    }
    public boolean reportarVoto(Candidato candidato, int idVoto) {
        Queue<Voto> votos = candidato.getVotosRecibidos();
        for(Voto voto : votos){
            if(voto.getID() == idVoto){
                votosReportados.add(voto);
                votos.remove(voto);
                return true;
            }
        }
        System.out.println("El voto reportado no se ha encontrado");
        return false;
    }
    public String obtenerResultados() {
        StringBuilder resultados = new StringBuilder();
        for (Candidato candidato : listaCandidatos) {
            resultados.append("Candidato ").append(candidato.getNombre())
                    .append(" (").append(candidato.getPartido()).append("): ")
                    .append(candidato.getVotosRecibidos().size()).append(" votos.\n");
        }
        return resultados.toString();
    }
    public void agregarCandidato(Candidato candidato) {
        listaCandidatos.add(candidato);
    }
    private String obtenerTimestamp() {
        return java.time.LocalTime.now().toString();
    }
}