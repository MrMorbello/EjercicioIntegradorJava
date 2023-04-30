package org.example;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conexion {

    Connection conexion = null;
    Statement stmt = null;
    String usuario = "root";
    String contraseña = "3578";
    String bd = "ejercicio_arg_programa_final";
    String ip = "localhost";
    String puerto = "3306";
    String ruta = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;
    //CREATE TABLE alumnos (nombre VARCHAR(50) NOT NULL, legajo VARCHAR(50) NOT NULL, materias_aprobadas JSON, PRIMARY KEY (legajo))
    //CREATE TABLE materias (nombre VARCHAR(50) NOT NULL, correlativas JSON, PRIMARY KEY (nombre))


    public void establecerConexion() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(ruta, usuario, contraseña);
            stmt = conexion.createStatement();
            //JOptionPane.showMessageDialog(null, "Se conecto correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO se pudo conectar con la DB" + e);
        }
    }

    public void cerrarConexion() {
        try {
            conexion.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String pasarObjetosAJson(Object objeto){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(objeto);
            return json;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> pasarDeJsonAListaStrings(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> lista = new ArrayList<String>();

        String[] arr = objectMapper.readValue(json, String[].class);

        for (String valor: arr) {
            lista.add(valor);
        }
        return lista;
    }

    public Alumno traerDatosAlumno(String legajo) {

        try {
            this.establecerConexion();

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

            ResultSet rs = stmt.executeQuery("SELECT * FROM alumnos WHERE legajo=\"" + legajo + "\";");
            rs.next();
            Alumno alumno = new Alumno(rs.getString("nombre"), rs.getString("legajo"));

            String jsonText = rs.getString("materias_aprobadas");
            List<String> nombreCorrelativas = pasarDeJsonAListaStrings(jsonText);

            alumno.setMateriasAprobadas(nombreCorrelativas);
            this.cerrarConexion();

            return alumno;

        } catch (JsonProcessingException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Materia traerDatosMateria(String nombre) {
        try {
            this.establecerConexion();

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

            ResultSet rs = stmt.executeQuery("SELECT * FROM materias WHERE nombre=\"" + nombre + "\";");
            rs.next();
            Materia materia = new Materia(rs.getString("nombre"));
            List<String> correlativas = pasarDeJsonAListaStrings(rs.getString("correlativas"));
            materia.setCorrelativas(correlativas);

            this.cerrarConexion();

            return materia;

        } catch (JsonProcessingException | SQLException e) {
            e.printStackTrace();
        }
            return null;
    }


    public void guardarDatosAlumno(Alumno alumno)  {
        this.establecerConexion();
        String query = "INSERT INTO alumnos (nombre, legajo, materias_aprobadas) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE legajo = VALUES(legajo), nombre = VALUES(nombre), materias_aprobadas = VALUES(materias_aprobadas);";
        try {

            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, alumno.getNombre());
            statement.setString(2, alumno.getLegajo());
            statement.setString(3, pasarObjetosAJson(alumno.getMateriasAprobadas()));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarDatosMateria(Materia materia)  {
        this.establecerConexion();
        String query = "INSERT INTO materias (nombre, correlativas) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE nombre = VALUES(nombre), correlativas = VALUES(correlativas);";
        try {

            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, materia.getNombre());
            statement.setString(2, pasarObjetosAJson(materia.getCorrelativas()));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
