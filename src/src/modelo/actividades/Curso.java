package modelo.actividades;

import certificacion.Certificable;
import modelo.Estudiante;

public class Curso extends Actividad implements Certificable {

    private int nivel;

    public Curso(int id, String titulo, int cupoMaximo, int nivel){
        super(id,titulo,cupoMaximo);
        this.nivel = nivel;
    }
    @Override
    public double calcularCostoMateriales(){
        return nivel * 3000.0;
    }

    @Override
    public String getTipo(){
        return "Curso";
    }
    public int getNivel() {
        return nivel;
    }
    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "Certificado emitido por " + ENTIDAD_EMISORA
                + " para " + estudiante.getNombre()
                + " por participar del curso " + getTitulo();
    }
}
