package analizador;

public class Atributo {
    private String accesibilidad;
    private String tipo;
    private String nombre;

    public Atributo(String accesibilidad, String tipo, String nombre) {
        this.accesibilidad = accesibilidad;
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public String getAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(String accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
