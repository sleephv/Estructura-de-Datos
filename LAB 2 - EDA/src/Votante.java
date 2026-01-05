public class Votante {
    private int id;
    private String nombre;
    private boolean yaVoto;
    Votante(int id, String nombre, boolean yaVoto) {
        this.id = id;
        this.nombre = nombre;
        this.yaVoto = false;
    }
    public int getID() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public boolean getYaVoto() {
        return yaVoto;
    }
    public void setID(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setYaVoto(boolean yaVoto) {
        this.yaVoto = yaVoto;
    }
    public void marcarComoVotado() {
        this.yaVoto = true;
    }
}