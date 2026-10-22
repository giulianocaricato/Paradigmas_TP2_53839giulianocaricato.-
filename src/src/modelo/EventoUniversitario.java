package modelo;

import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Taller;
import modelo.actividades.Curso;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class EventoUniversitario implements Serializable {

    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }


    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.sala = otro.sala;
        this.actividades = new ArrayList<>(otro.actividades);
        cantidadEventos++;
    }


    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0.0;
        }
        double costoActividades = 0.0;
        for (Actividad a : actividades) {
            costoActividades += a.calcularCostoMateriales();
        }
        return (costoBase + costoActividades) * 1.21;
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }


    public void crearActividad(int id, String titulo, int cupo, String tipo, String detalle) {

        Actividad nueva;
        if ("Charla".equalsIgnoreCase(tipo)) {
            nueva = new Charla(id, titulo, cupo, detalle);
        } else if ("Taller".equalsIgnoreCase(tipo)) {
            boolean requiereNotebook = Boolean.parseBoolean(detalle);
            nueva = new Taller(id, titulo, cupo, requiereNotebook);
        }else if ("Curso".equalsIgnoreCase(tipo)){

            int nivel = Integer.parseInt(detalle);
            nueva = new Curso(id, titulo, cupo, nivel);
        } else {
            throw new IllegalArgumentException("Tipo de actividad no soportado: " + tipo);
        }
        actividades.add(nueva);
    }

    public void mostrarDatos() {
        System.out.println("===== Evento Universitario =====");
        System.out.println("Id: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Gratuito: " + (gratuito ? "Si" : "No"));
        System.out.printf("Costo estimado: $%.2f%n", calcularCostoEstimado());
        System.out.println("Sala asignada: " + (sala != null ? sala : "(sin asignar)"));
        System.out.println("Actividades (" + actividades.size() + "):");
        for (Actividad a : actividades) {
            a.mostrarIdentificacion();
        }
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public boolean isGratuito() {
        return gratuito;
    }

    public Sala getSala() {
        return sala;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public boolean persistirEvento() throws IOException {
        try (ObjectOutputStream salida = new ObjectOutputStream(
                new FileOutputStream(id + ".dat"))) {
            salida.writeObject(this);
            return true;
        }
    }
    public static EventoUniversitario recuperarEvento(String id)
        throws IOException, ClassNotFoundException {

      try (ObjectInputStream entrada = new ObjectInputStream(
              new FileInputStream(id + ".dat"))) {

          return (EventoUniversitario) entrada.readObject();
      }
    }
    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo){

        List<T> resultado = new ArrayList<>();
        for (Actividad actividad : actividades) {
            if (tipo.isInstance(actividad)){
                resultado.add(tipo.cast(actividad));
            }
        }
        return resultado;
    }
    public double calcularCostoMateriales(List<? extends Actividad> actividades){

        double total = 0.0;

        for (Actividad actividad : actividades) {
            total += actividad.calcularCostoMateriales();
        }
        return total;
    }
}
