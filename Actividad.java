package modelo.actividades;

import modelo.Estudiante;
import modelo.Inscripcion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import excepciones.CupoExcedidoException;
import java.io.Serializable;


public abstract class Actividad implements Serializable {

    public static final int CUPO_MINIMO = 5;

    private int id;
    private String titulo;
    private int cupoMaximo;
    private List<Inscripcion> inscripciones;

    protected Actividad(int id, String titulo, int cupoMaximo) {
        if (cupoMaximo < CUPO_MINIMO) {
            throw new IllegalArgumentException(
                    "El cupo máximo (" + cupoMaximo + ") no puede ser menor al cupo mínimo (" + CUPO_MINIMO + ")");
        }
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }


    public Inscripcion inscribir(Estudiante estudiante)
            throws CupoExcedidoException {
        if (estudiante == null) {
            throw new IllegalArgumentException("No se puede inscribir un estudiante nulo");
        }

        if (inscripciones.size() >= cupoMaximo) {
            throw new CupoExcedidoException(
                    "No se pudo inscribir a " + estudiante.getNombre()
                            + ": cupo completo en '" + titulo + "'"
            );
        }

        Inscripcion inscripcion = new Inscripcion(
                LocalDate.now(),
                "Pendiente",
                estudiante,
                this
        );

        inscripciones.add(inscripcion);
        return inscripcion;
    }

    public void mostrarInscripciones() {
        System.out.println("Inscripciones en '" + titulo + "' (" + inscripciones.size() + "):");
        if (inscripciones.isEmpty()) {
            System.out.println("    (sin inscripciones)");
        }
        for (Inscripcion i : inscripciones) {
            System.out.println("    - " + i.getEstudiante().getNombre()
                    + " (legajo " + i.getEstudiante().getLegajo() + ") - "
                    + i.getEstado() + " - " + i.getFecha());
        }
    }


    public final void mostrarIdentificacion() {
        System.out.printf("    [%s] Id: %d - %s - Cupo: %d - Costo materiales: $%.2f%n",
                getTipo(), id, titulo, cupoMaximo, calcularCostoMateriales());
    }

    public abstract double calcularCostoMateriales();

    public abstract String getTipo();

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public List<Inscripcion> getInscripciones() {

        return inscripciones;
    }
}
