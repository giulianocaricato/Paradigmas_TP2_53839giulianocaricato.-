package hilos;

import modelo.EventoUniversitario;
import modelo.Inscripcion;
import modelo.actividades.Actividad;

public class EnvioTicketsThread extends Thread {

    private final EventoUniversitario evento;

    public EnvioTicketsThread(EventoUniversitario evento){
        super("EnvioTickets");
        this.evento = evento;
    }
    @Override

    public void run(){

        System.out.println("Iniciando hilo: " + getName());
        for (Actividad actividad : evento.getActividades()){
            for (Inscripcion inscripcion : actividad.getInscripciones()){
                if ("Confirmada".equalsIgnoreCase(inscripcion.getEstado()) && inscripcion.getTicket() != null){

                    System.out.println("[" + getName() + "] Enviando: " + inscripcion.getTicket());
                    try {
                        Thread.sleep(600);
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                        return;
                    }
                    System.out.println("[" + getName() + "] Ticket enviado.");
                }
            }
        }
        System.out.println("Finalizo el hilo de envio.");
    }
}
