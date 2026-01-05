public class Game{
    String nombre;
    String categoria;
    int precio;
    int calidad;
    Game(String nombre, String categoria, int precio, int calidad){
        this.nombre=nombre;
        this.categoria=categoria;
        this.precio=precio;
        this.calidad=calidad;
    }
    String getNombre(){return nombre; }
    String getCategoria(){ return categoria; }
    int getPrecio(){ return precio; }
    int getCalidad(){ return calidad; }
}