package modelo;


import java.io.Serializable;

public class Estudiante implements Serializable {

    private String legajo;
    private String nombre;

    public Estudiante(String legajo, String nombre) {
        if (legajo == null || legajo.isBlank()){
            throw new IllegalArgumentException("El legajo no puede estar vacio");
        }
        if (nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.legajo = legajo.trim();
        this.nombre = nombre.trim();
    }

    public String getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (legajo " + legajo + ")";
    }
}
