package org.example;
import java.util.List;

public class Materia {
    String nombre;
    List<String> correlativas;

    public Materia(String unNombre) {
        nombre = unNombre;
    }

    public void setCorrelativas(List<String> unaListaDeCorrelativas) {
        correlativas = unaListaDeCorrelativas;
    }

    public boolean esAplicableCon(List<String> unaListaDeMateriasAprobadas) {
        return unaListaDeMateriasAprobadas.containsAll(correlativas);
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getCorrelativas() {
        return correlativas;
    }
}
