// Sistema de votación universitario
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UrnaElectoral urna = new UrnaElectoral();
        System.out.println("Ingrese cantidad de candidatos: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < cantidad; i++) {
            System.out.println("Ingrese datos del candidato " + (i + 1));
            System.out.println("Ingrese el id del candidato: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese el nombre del candidato: ");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el nombre del partido de " + nombre + ":");
            String partido = scanner.nextLine();
            urna.agregarCandidato(new Candidato(id, nombre, partido));
        }
        System.out.println("Ingrese la cantidad de votantes: ");
        int cantidadV = scanner.nextInt();
        scanner.nextLine();
        Votante[] listaVotantes = new Votante[cantidadV];
        for (int i = 0; i < cantidadV; i++) {
            System.out.println("Ingrese datos del votante " + (i + 1));
            System.out.println("Ingrese el id del votante: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese el nombre del votante: ");
            String nombre = scanner.nextLine();
            listaVotantes[i] = new Votante(id, nombre, false);
        }
        for (Votante votante : listaVotantes) {
            if (urna.verificarVotante(votante)) {
                System.out.println(votante.getNombre() + ", no puedes votar nuevamente porque ya has votado.");
                continue;
            }
            System.out.println(votante.getNombre() + ", ingrese la hora actual (formato HH:MM:SS): ");
            String timestamp = scanner.nextLine();
            System.out.println(votante.getNombre() + ", ¿por qué candidato deseas votar?");
            System.out.println("Candidatos disponibles:");
            for (Candidato candidato : urna.getListaCandidatos()) {
                System.out.println(candidato.getID() + ". " + candidato.getNombre() + " (" + candidato.getPartido() + ")");
            }
            int candidatoID = scanner.nextInt();
            scanner.nextLine();
            if (urna.registrarVoto(votante, candidatoID)) {
                System.out.println("Voto registrado con éxito a las " + timestamp);
            } else {
                System.out.println("No se pudo registrar tu voto. Verifica la información ingresada.");
            }
        }
        System.out.println("Resultados de la votación:");
        System.out.println(urna.obtenerResultados());
        scanner.close();
    }
}