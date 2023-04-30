package org.example;

public class Inscripcion {
    Alumno alumno;
    Materia materia;
    boolean aprobada;
    public Inscripcion(Alumno unAlumno, Materia unaMateria) {
        alumno = unAlumno;
        materia = unaMateria;
        this.actualizarEstado();
    }

    public void actualizarEstado() {
        aprobada = alumno.sePuedeInscribirA(materia);
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public boolean estaAprobada() {
        return aprobada;
    }
}
