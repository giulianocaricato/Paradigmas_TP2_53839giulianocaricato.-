import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Inscripcion;
import modelo.Sala;
import modelo.actividades.Actividad;
import excepciones.CupoExcedidoException;
import certificacion.Certificable;
import modelo.actividades.Curso;
import modelo.actividades.Charla;
import modelo.actividades.Taller;
import hilos.EnvioTicketsThread;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class App {

    public static void main(String[] args) {

        // a. Se crean 3 estudiantes
        Estudiante est1 = new Estudiante("53467", "Valentin Ressia");
        Estudiante est2 = new Estudiante("54321", "Juan Lopez");
        Estudiante est3 = new Estudiante("12345", "Martin Perez");

        // b. Se crea 1 evento
        EventoUniversitario evento = new EventoUniversitario("EVT-01", "Jornada de Programacion", 10000.0, false);

        // c. Se crea 1 sala y se asigna al evento
        Sala sala = new Sala(1, "Aula Magna");
        evento.asignarSala(sala);

        // d. Se crean 2 actividades para el evento: una modelo.actividades.Charla y un modelo.actividades.Taller
        evento.crearActividad(1, "Introduccion a la POO", 30, "Charla", "Prof. Ana Gomez");
        evento.crearActividad(2, "Taller de Java Avanzado", 20, "Taller", "true");
        evento.crearActividad(3,"Curso de Java", 20, "Curso","2" );

        Actividad charla = evento.getActividades().get(0);
        Actividad taller = evento.getActividades().get(1);
        Actividad curso = evento.getActividades().get(2);

      try{

        charla.inscribir(est1);
        taller.inscribir(est2);
        curso.inscribir(est3);

    } catch (CupoExcedidoException e){

        System.out.println("Error de cupo: " + e.getMessage());
    }

        System.out.println("\n##### CERTIFICADOS #####");
      if (taller instanceof Certificable){
          Certificable certificableTaller = (Certificable) taller;
          System.out.println(certificableTaller.generarCertificado(est2));
      }
      if (curso instanceof Certificable) {
          Certificable certificableCurso = (Certificable) curso;
          System.out.println(certificableCurso.generarCertificado(est3));
      }

      System.out.println("\n##### DATOS DEL EVENTO #####");
      evento.mostrarDatos();

      try {
          charla.inscribir(est1);
          charla.inscribir(est2);

          taller.inscribir(est2);
          taller.inscribir(est3);

          System.out.println("Inscripciones realizadas correctamente.");

          evento.persistirEvento();
          System.out.println("Evento guardado correctamente.");

          EventoUniversitario eventoRecuperado =
                  EventoUniversitario.recuperarEvento(evento.getId());

          System.out.println("Evento recuperado correctamente.");
          eventoRecuperado.mostrarDatos();
      } catch (CupoExcedidoException e) {

          System.out.println("Error de cupo: " + e.getMessage());

      } catch (FileNotFoundException e) {

          System.out.println("No se encontro el archivo: " + e.getMessage());

      } catch (IOException e) {

          System.out.println("Error al guardar o leer el archivo: " + e.getMessage());

      } catch (ClassNotFoundException e) {

          System.out.println("Error al reconstruir el evento: " + e.getMessage());

      } finally {

          System.out.println("Finalizo el proceso de inscripcion y persistencia");
      }

        System.out.println("\n##### CASO FALLIDO: CUPO EXCEDIDO #####");

        evento.crearActividad(
                3,
                "Taller con cupo limitado",
                5,
                "Taller",
                "false"
        );

        Actividad actividadCupo = evento.getActividades().get(3);

        Estudiante est4 = new Estudiante("11111", "Alumno 4");
        Estudiante est5 = new Estudiante("22222", "Alumno 5");
        Estudiante est6 = new Estudiante("33333", "Alumno 6");
        Estudiante est7 = new Estudiante("44444", "Alumno 7");
        Estudiante est8 = new Estudiante("55555", "Alumno 8");
        Estudiante est9 = new Estudiante("66666", "Alumno 9");

        try {

            actividadCupo.inscribir(est4);
            actividadCupo.inscribir(est5);
            actividadCupo.inscribir(est6);
            actividadCupo.inscribir(est7);
            actividadCupo.inscribir(est8);

            // Esta es la sexta inscripción y el cupo es 5
            actividadCupo.inscribir(est9);

        } catch (CupoExcedidoException e) {

            System.out.println("Error controlado: " + e.getMessage());
        }

        System.out.println("\n########## RESUMEN DEL EVENTO ##########\n");
        evento.mostrarDatos();

        System.out.println();
        charla.mostrarInscripciones();
        taller.mostrarInscripciones();

        System.out.println("\nTotal de eventos creados: " + EventoUniversitario.getCantidadEventos());


        System.out.println("\n########## DEMOSTRACION CONSTRUCTOR DE COPIA ##########\n");

        EventoUniversitario eventoOriginal = new EventoUniversitario("EVT-02", "Hackathon UTN", 5000.0, true);
        eventoOriginal.asignarSala(new Sala(2, "Laboratorio 3"));
        eventoOriginal.crearActividad(1, "Charla de apertura", 50, "Charla", "Ing. Laura Diaz");

        EventoUniversitario eventoCopia = new EventoUniversitario(eventoOriginal);

        System.out.println("--- Evento original ---");
        eventoOriginal.mostrarDatos();
        System.out.println("\n--- Copia del evento ---");
        eventoCopia.mostrarDatos();

        System.out.println("\nTotal de eventos creados: " + EventoUniversitario.getCantidadEventos());
        System.out.println("\n##### EJERCICIO 3 #####");

        List<Charla> charlas = evento.filtrarActividadesPorTipo(Charla.class);

        List<Taller> talleres = evento.filtrarActividadesPorTipo(Taller.class);

        List<Curso> cursos = evento.filtrarActividadesPorTipo(Curso.class);

        System.out.println("Charlas: " + charlas.size());
        System.out.println("Talleres: " + talleres.size());
        System.out.println("Cursos: " + cursos.size());

        System.out.println("\n##### COSTO DE MATERIALES #####");

        System.out.println("Costo de charlas: $" + evento.calcularCostoMateriales(charlas));
        System.out.println("Costo de talleres: $" + evento.calcularCostoMateriales(talleres));
        System.out.println("Costo de cursos: $" + evento.calcularCostoMateriales(cursos));

        System.out.println("\n##### EJERCICIO 4 #####");

        EventoUniversitario evento4 = new EventoUniversitario("EVT-04", "Congreso de Tecnologia", 10000.0,false);

        evento4.asignarSala(new Sala(4,"Auditorio Principal"));

        evento4.crearActividad(1,"Charla de IA",20,"Charla","Profesor Lopez");
        evento4.crearActividad(2,"Taller de Java", 15,"Taller", "true");
        evento4.crearActividad(3,"Curso de Python", 20, "Curso","2");

        Actividad charla4 = evento4.getActividades().get(0);
        Actividad taller4 = evento4.getActividades().get(1);
        Actividad curso4 = evento4.getActividades().get(2);

        try{
            Inscripcion ins1 = charla4.inscribir(est1);
            Inscripcion ins2 = taller4.inscribir(est2);
            Inscripcion ins3 = curso4.inscribir(est3);

            ins1.confirmar();
            ins2.confirmar();

            System.out.println("\n##### TICKETS GENERADOS #####");

            System.out.println(ins1.generarTicket());
            System.out.println(ins2.generarTicket());

            System.out.println("Estado de la tercera inscripcion: " + ins3.getEstado());
            EnvioTicketsThread hilo = new EnvioTicketsThread(evento4);

            hilo.start();

            System.out.println("\n##### HILO PRINCIPAL #####");
            System.out.println("Ejecutando: " + Thread.currentThread().getName());

            evento4.mostrarDatos();

            for (Actividad actividad : evento4.getActividades()){

                System.out.println("\nActividad: " + actividad.getTitulo());

                for (Inscripcion inscripcion : actividad.getInscripciones()){
                    System.out.println("Estudiante: " + inscripcion.getEstudiante().getNombre() + "| Estado: " + inscripcion.getEstado());
                }
            }
            try {
                hilo.join();
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Se interrumpio la espera del hilo.");
            }
        }catch (CupoExcedidoException e){
            System.out.println("Error de cupo: " + e.getMessage());
        }

    }


}
