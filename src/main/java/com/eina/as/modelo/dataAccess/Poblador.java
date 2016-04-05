package com.eina.as.modelo.dataAccess;

import java.sql.SQLException;

/**
 * Created by naxsel on 15/02/16.
 */
//STEP 1. Import required packages
import java.sql.*;
import java.util.Locale;

public class Poblador {

    /**
     * Driver para conectar con MYSQL
     */
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    /**
     * Conexion a una BD MYSQL
     */
    private static final String DB_URL = "jdbc:mysql://localhost";

    /**
     * CaDena de caracteres con el nombre de usuario, o login, a emplear para
     * conectarse a la BD
     */
    private static final String USER = "root";
    /**
     * Cadena de caracteres con el password, o contraseña, a emplear para
     * conectarse a la BD
     */
    private static final String PASS = "root";
    /**
     * Conexion con la BD
     */
    private Connection connection = null;

    /**
     * Metodo constructor. Asigna los valores de usuario, password, host, puerto
     * y nombre de la bd, para que posteriormente pueda hacerse la conexion
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Poblador() throws InstantiationException,IllegalAccessException, ClassNotFoundException {

        Locale.setDefault(Locale.UK);

        Class.forName(JDBC_DRIVER);
    }

    /**
     * Crea una Conexion a la BBDD
     * @throws SQLException
     */
    public void connect() throws SQLException {
        // Estableciendo la conexion con la BD
        connection = DriverManager.getConnection(DB_URL,USER,PASS);
        Statement stmt = connection.createStatement();
        stmt.executeQuery("USE webas");
        stmt.close();
    }

    /**
     * Crea el Estado
     * @return
     */
    public Statement getStatement() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    /**
     * Metodo para cerrar la conexion JDBC con la BD
     */
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (SQLException sqlE) {
            connection = null;
        }
    }

    /**
     * Ejecucion de una sentencia SQL
     * @param sql
     * @param params
     */
    public void executeSentence(String sql, Object...params) {
        PreparedStatement stmt = null;
        try {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            stmt = connection.prepareStatement(sql);
            for(int i = 0; i<params.length; i++) {
                stmt.setObject(i+1, params[i]);
            }
            int resultado = stmt.executeUpdate();
            System.out.println(resultado);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        Poblador mt = null;
        try{
            mt = new Poblador();

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            mt.connect();
            System.out.println("Done");

            //STEP 4: Create tables if necessary
            StringBuffer sb = new StringBuffer();

            System.out.println("Ejecutando comprobaciones previas...");

            mt.executeSentence("DROP TABLE IF EXISTS spr_userfoto");
            mt.executeSentence("DROP TABLE IF EXISTS spr_usercarrera");
            mt.executeSentence("DROP TABLE IF EXISTS spr_citacarrera");
            mt.executeSentence("DROP TABLE IF EXISTS spr_userevento");
            mt.executeSentence("DROP TABLE IF EXISTS spr_evento");
            mt.executeSentence("DROP TABLE IF EXISTS spr_foto");
            mt.executeSentence("DROP TABLE IF EXISTS spr_usuario");
            mt.executeSentence("DROP TABLE IF EXISTS spr_cita");
            mt.executeSentence("DROP TABLE IF EXISTS spr_carrerafacultad");


            System.out.println("Creando la tabla de facultad/carrera...");
            sb.append("CREATE TABLE IF NOT EXISTS spr_carrerafacultad (");
            sb.append("facultad VARCHAR (100) NOT NULL,");
            sb.append("carrera VARCHAR (100) NOT NULL,");
            sb.append("PRIMARY KEY (facultad,carrera))");

            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de citas...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS spr_cita(");
            sb.append("hora VARCHAR(20),");
            sb.append("fecha VARCHAR (20),");
            sb.append("lugar VARCHAR (100),");
            sb.append("libres INT,");
            sb.append("totales INT,");
            sb.append("PRIMARY KEY (hora,fecha))");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de usuarios...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS spr_usuario(");
            sb.append("email VARCHAR (100) NOT NULL PRIMARY KEY,");
            sb.append("pass VARCHAR (100) NOT NULL,");
            sb.append("nombre VARCHAR (100) NOT NULL,");
            sb.append("apellidos VARCHAR (100) NOT NULL,");
            sb.append("anno INT,");
            sb.append("admin INT)");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de fotos...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS spr_foto(");
            sb.append("url VARCHAR (100) NOT NULL PRIMARY KEY)");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de eventos...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS spr_evento(");
            sb.append("nombre VARCHAR (100),");
            sb.append("fecha VARCHAR(20),");
            sb.append("tipo VARCHAR(20),");
            sb.append("PRIMARY KEY (nombre,fecha))");
            mt.executeSentence(sb.toString());


            System.out.println("Creando la tabla de Usuario/Foto...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS spr_userfoto(");
            sb.append("email VARCHAR (100) NOT NULL,");
            sb.append("url VARCHAR (100) NOT NULL,");
            sb.append("FOREIGN KEY (email) REFERENCES spr_usuario(email),");
            sb.append("FOREIGN KEY (url) REFERENCES spr_foto(url),");
            sb.append("PRIMARY KEY (email,url))");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de cita/carrera...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS spr_citacarrera(");
            sb.append("facultad VARCHAR (100) NOT NULL,");
            sb.append("carrera VARCHAR (100) NOT NULL,");
            sb.append("hora VARCHAR(20),");
            sb.append("fecha VARCHAR (20),");
            sb.append("FOREIGN KEY (facultad,carrera) REFERENCES spr_carrerafacultad(facultad,carrera),");
            sb.append("FOREIGN KEY (hora,fecha) REFERENCES spr_cita(hora,fecha),");
            sb.append("PRIMARY KEY (hora,fecha,carrera,facultad))");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de Usuario/Carrera...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS spr_usercarrera(");
            sb.append("email VARCHAR (100) NOT NULL,");
            sb.append("facultad VARCHAR (100) NOT NULL,");
            sb.append("carrera VARCHAR (100) NOT NULL,");
            sb.append("FOREIGN KEY (email) REFERENCES spr_usuario(email),");
            sb.append("FOREIGN KEY (facultad,carrera) REFERENCES spr_carrerafacultad(facultad,carrera),");
            sb.append("PRIMARY KEY (email,facultad,carrera))");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de Usuario/Evento...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS spr_userevento(");
            sb.append("nombre VARCHAR (100) NOT NULL,");
            sb.append("fecha VARCHAR (20) NOT NULL,");
            sb.append("user VARCHAR (100) NOT NULL,");
            sb.append("FOREIGN KEY (nombre,fecha) REFERENCES spr_evento(nombre,fecha),");
            sb.append("FOREIGN KEY (user) REFERENCES spr_usuario(email),");
            sb.append("PRIMARY KEY (nombre,fecha,user))");

            mt.executeSentence(sb.toString());



        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            if(mt!=null)
                mt.disconnect();
        }//end try
        System.out.println("Goodbye!");
    }//end Poblador
}