package org.example;
import java.util.List;

public class Alumno {
    String nombre;
    String legajo;
    List<String> materiasAprobadas;

    public Alumno(String unNombre, String unLegajo) {
        nombre = unNombre;
        legajo = unLegajo;
    }

    public void setMateriasAprobadas(List<String> correlativasAprobadas) {
        materiasAprobadas = correlativasAprobadas;
    }

    public boolean sePuedeInscribirA(Materia unaMateria) {
        return unaMateria.esAplicableCon(materiasAprobadas);
    }

    public String getNombre() {
        return nombre;
    }

    public String getLegajo() {
        return legajo;
    }

    public List<String> getMateriasAprobadas() {
        return materiasAprobadas;
    }
}

