package modelo;

import java.time.LocalDate;
import modelo.actividades.Actividad;
import java.io.Serializable;
import java.util.UUID;


public class Inscripcion implements Serializable {

    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;
    private Actividad actividad;
    private TicketDeAcceso ticket;

    public Inscripcion(LocalDate fecha, String estado, Estudiante estudiante, Actividad actividad) {
        this.fecha = fecha;
        this.estado = estado;
        this.estudiante = estudiante;
        this.actividad = actividad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Actividad getActividad() {
        return actividad;
    }

    @Override
    public String toString() {
        return estudiante.getNombre() + " -> " + actividad.getTitulo() + " (" + estado + ", " + fecha + ")";
    }
    public void confirmar(){
        this.estado = "Confirmada";
    }
    public TicketDeAcceso generarTicket() {

        if (!"Confirmada".equalsIgnoreCase(estado)) {
            throw new IllegalStateException(
                    "La inscripción no está confirmada"
            );
        }

        if (ticket == null) {
            ticket = new TicketDeAcceso();
        }

        return ticket;
    }

    public TicketDeAcceso getTicket() {
        return ticket;
    }


    public class TicketDeAcceso implements Serializable {

        private final String idTicket;
        private final LocalDate fechaEmision;

        private TicketDeAcceso() {
            this.idTicket = UUID.randomUUID().toString();
            this.fechaEmision = LocalDate.now();
        }

        public String getIdTicket() {
            return idTicket;
        }

        public LocalDate getFechaEmision() {
            return fechaEmision;
        }

        @Override
        public String toString() {
            return "Ticket: " + idTicket
                    + " | Fecha: " + fechaEmision
                    + " | Estudiante: " + estudiante.getNombre()
                    + " | Actividad: " + actividad.getTitulo();
        }
    }
}
