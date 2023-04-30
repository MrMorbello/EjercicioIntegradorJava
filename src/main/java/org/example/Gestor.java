package org.example;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gestor {
    Conexion conexion;
    public Gestor() {
        conexion = new Conexion();
        this.elegirOpcion();
    }
    private void mostrarMenu() {
        System.out.println("---------------------------------");
        System.out.println("Seleccione una opción");
        System.out.println("1 - Agregar Alumno");
        System.out.println("2 - Agregar Materia");
        System.out.println("3 - Inscribir Alumno a Materia");
        System.out.println("4 - Salir");
    }

    public void elegirOpcion() {
        this.mostrarMenu();
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        while (true) {
            switch (opcion) {
                case 1:
                    this.agregarAlumno();
                    break;
                case 2:
                    this.agregarMateria();
                    break;
                case 3:
                    this.crearInscripcion();
                    break;
                case 4:
                    System.out.println("Saliendo del programa");
                    return;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
            this.mostrarMenu();
            opcion = sc.nextInt();
        }
    }

    private void agregarAlumno() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el legajo");
        String legajo = sc.nextLine();

        if (!this.esLegajoValido(legajo)) {
            System.out.println("Ese no es un legajo válido");
            return;
        }

        System.out.println("Ingrese el nombre");
        String nombre = sc.nextLine();

        System.out.println("A continuación indique las materias aprobadas. Cuando finalice introduzca . (puntito)");
        ArrayList<String> materiasAprobadas = new ArrayList<String>();


        while (true) {
            String materia = sc.nextLine();
            if(materia.equals(".")) {break;}
            materiasAprobadas.add(materia);
        }
        Alumno alumno = new Alumno(nombre, legajo);
        alumno.setMateriasAprobadas(materiasAprobadas);
        System.out.println("guardando información...");

        conexion.guardarDatosAlumno(alumno);
        System.out.println("Alumno guardado correctamente");
    }
    private boolean esLegajoValido(String legajo) {
        String expresion = "\\d{5}";

        Pattern patron = Pattern.compile(expresion);
        Matcher matcher = patron.matcher(legajo);

        return matcher.matches();
    }

    private void agregarMateria() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el nombre");
        String nombre = sc.nextLine();

        System.out.println("A continuación indique las materias correlativas. Cuando finalice introduzca . (puntito)");
        ArrayList<String> materiasCorrelativas = new ArrayList<String>();

        while (true) {
            String materia = sc.nextLine();
            if(materia.equals(".")) {break;}
            materiasCorrelativas.add(materia);
        }

        Materia objetoMateria = new Materia(nombre);
        objetoMateria.setCorrelativas(materiasCorrelativas);

        conexion.guardarDatosMateria(objetoMateria);
        System.out.println("Materia guardada correctamente");
    }

    private void crearInscripcion() {
        Scanner sc = new Scanner(System.in);


        System.out.println("Ingrese el legajo");
        String legajo = sc.nextLine();

        System.out.println("Ingrese el nombre de la Materia");
        String nombre = sc.nextLine();

        Alumno alumno = conexion.traerDatosAlumno(legajo);
        Materia materia = conexion.traerDatosMateria(nombre);

        Inscripcion inscripcion = new Inscripcion(alumno, materia);

        if(inscripcion.aprobada) {
            System.out.println("La inscripción está aprobada");
        } else {
            System.out.println("La inscripción NO está aprobada");
        }

    }

}
