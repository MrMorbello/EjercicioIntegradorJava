import org.example.Alumno;
import org.example.Inscripcion;
import org.example.Materia;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class InscripcionTest {
    @Test
    public void test01MateriaSinCorrelativasYAlumnoSinMaterias(){
        System.out.println();
        System.out.println("test01 un alumno se puede inscribir a una materia sin correlativas");

        Materia algo1 = new Materia("Algo1");
        algo1.setCorrelativas(new ArrayList<String>());

        Alumno alumno = new Alumno("alumno", "11111");
        alumno.setMateriasAprobadas(new ArrayList<String>());

        Inscripcion inscripcion = new Inscripcion(alumno, algo1);

        System.out.println(inscripcion.estaAprobada());
    }
    @Test
    public void test02MateriaConCorrelativasYAlumnoConMaterias(){
        System.out.println();
        System.out.println("test02 un alumno se puede inscribir porque tiene las correlativas");

        Materia algo1 = new Materia("Algo1");
        algo1.setCorrelativas(new ArrayList<String>());

        List<String> correlativas = new ArrayList<String>();
        correlativas.add("Algo1");
        Materia algo2 = new Materia("Algo2");
        algo2.setCorrelativas(correlativas);


        Alumno alumno = new Alumno("alumno", "11111");
        alumno.setMateriasAprobadas(correlativas);

        Inscripcion inscripcion = new Inscripcion(alumno, algo2);

        System.out.println(inscripcion.estaAprobada());
    }
    @Test
    public void test03MateriaConCorrelativasYAlumnoConOtrasMaterias(){
        System.out.println();
        System.out.println("test03 un alumno se puede inscribir teniendo OTRAS aprobadas");

        Materia algo1 = new Materia("Algo1");
        algo1.setCorrelativas(new ArrayList<String>());

        Materia analisis = new Materia("Analisis");
        analisis.setCorrelativas(new ArrayList<String>());

        List<String> correlativas = new ArrayList<String>();
        correlativas.add("Algo1");
        Materia algo2 = new Materia("Algo2");
        algo2.setCorrelativas(correlativas);

        List<String> aprobadas = new ArrayList<String>();
        aprobadas.add("analisis");

        Alumno alumno = new Alumno("alumno", "11111");
        alumno.setMateriasAprobadas(aprobadas);

        Inscripcion inscripcion = new Inscripcion(alumno, algo2);

        System.out.println(inscripcion.estaAprobada());
    }
    @Test
    public void test04UnaMateriaConCorrelativasYAlumnoSinMaterias(){
        System.out.println();
        System.out.println("test04 un alumno se puede inscribir aunque NO tiene las correlativas");

        Materia algo1 = new Materia("Algo1");
        algo1.setCorrelativas(new ArrayList<String>());

        List<String> correlativas = new ArrayList<String>();
        correlativas.add("Algo1");
        Materia algo2 = new Materia("Algo2");
        algo2.setCorrelativas(correlativas);

        Alumno alumno = new Alumno("alumno", "11111");
        alumno.setMateriasAprobadas(new ArrayList<String>());

        Inscripcion inscripcion = new Inscripcion(alumno, algo2);

        System.out.println(inscripcion.estaAprobada());
    }
}
