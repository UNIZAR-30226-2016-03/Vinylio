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

            mt.executeSentence("DROP TABLE IF EXISTS likeActividad");
            mt.executeSentence("DROP TABLE IF EXISTS likePublicacion");
            mt.executeSentence("DROP TABLE IF EXISTS participaActividad");
            mt.executeSentence("DROP TABLE IF EXISTS actividad");
            mt.executeSentence("DROP TABLE IF EXISTS publicacion");
            mt.executeSentence("DROP TABLE IF EXISTS amistades");
            mt.executeSentence("DROP TABLE IF EXISTS juegoUsuario");
            mt.executeSentence("DROP TABLE IF EXISTS juego");
            mt.executeSentence("DROP TABLE IF EXISTS usuario");


            System.out.println("Creando la tabla de usuarios...");
            sb.append("CREATE TABLE IF NOT EXISTS usuario (");
            sb.append("nick NOT NULL NOT NULL PRIMARY KEY ");
            sb.append("nombreYApellidos NOT NULL CHAR(64)");
            sb.append("correo NOT NULL UNIQUE CHAR(64)");
            sb.append("contrasenya NOT NULL CHAR(20)	 ");
            sb.append("urlImagen CHAR(256)");
            sb.append("fechaDeNacimiento NOT NULL DATE");
            sb.append("biografia CHAR(256)");
            sb.append("localizacion CHAR(32)");
            sb.append("urlTwitter CHAR(256)");
            sb.append("urlFacebook CHAR(256)");
            sb.append("urlPersonal CHAR(256));");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de juegos...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS juego(");
            sb.append("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("nombre NOT NULL CHAR(64),");
            sb.append("descripcion NOT NULL CHAR(10000));");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de juegos/usuarios...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS juegoUsuario(");
            sb.append("emailid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("emailidJuego INT NOT NULL,");
            sb.append("emailidUsuario INT NOT NULL,");
            sb.append("emailFOREIGN KEY (idUsuario) REFERENCES Usuario(nick),");
            sb.append("emailFOREIGN KEY (idJuego) REFERENCES Juego(id));");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de amistades...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS amistades(");

            sb.append("idUsuarioPrimero INT REFERENCES Usuario(nick) NOT NULL,");
            sb.append("idUsuarioSegundo INT REFERENCES Usuario(nick) NOT NULL,");
            sb.append("aceptado BOOLEAN NOT NULL DEFAULT FALSE,");
            sb.append("PRIMARY KEY (idUsuarioPrimero, idUsuarioSegundo));");

            System.out.println("Creando la tabla de publicaciones...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS publicacion(");
            sb.append("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("texto NOT NULL CHAR(256),");
            sb.append("idUsuario NOT NULL,");
            sb.append("FOREIGN KEY (idUsuario) REFERENCES Usuario(nick));");

            mt.executeSentence(sb.toString());

            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de actividades...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS actividad(");
            sb.append("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("descripcion NOT NULL CHAR(128),");
            sb.append("idJuego NOT NULL,");
            sb.append("FOREIGN KEY (idJuego) REFERENCES Juego(id));");

            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de usuarios/actividades...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS participaActividad(");
            sb.append("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("idUsuario INT NOT NULL,");
            sb.append("idActividad INT NOT NULL,");
            sb.append("FOREIGN KEY (idUsuario) REFERENCES Usuario(nick),");
            sb.append("FOREIGN KEY (idActividad) REFERENCES Actividad(id));");

            sb.append("facultad VARCHAR (100) NOT NULL,");
            sb.append("carrera VARCHAR (100) NOT NULL,");
            sb.append("hora VARCHAR(20),");
            sb.append("fecha VARCHAR (20),");
            sb.append("FOREIGN KEY (facultad,carrera) REFERENCES usuario(facultad,carrera),");
            sb.append("FOREIGN KEY (hora,fecha) REFERENCES juego(hora,fecha),");
            sb.append("PRIMARY KEY (hora,fecha,carrera,facultad))");
            mt.executeSentence(sb.toString());


            System.out.println("Creando la tabla de likes de actividades...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS likeActividad(");

            sb.append("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("idUsuario INT NOT NULL,");
            sb.append("idActividad INT NOT NULL,");
            sb.append("FOREIGN KEY (idUsuario) REFERENCES Usuario(nick),");
            sb.append("FOREIGN KEY (idActividad) REFERENCES Actividad(id));");
            sb.append("email VARCHAR (100) NOT NULL,");
            sb.append("url VARCHAR (100) NOT NULL,");
            sb.append("FOREIGN KEY (email) REFERENCES juegoUsuario(email),");
            sb.append("FOREIGN KEY (url) REFERENCES amistades(url),");
            sb.append("PRIMARY KEY (email,url))");
            mt.executeSentence(sb.toString());



            System.out.println("Creando la tabla de likes de publicaciones...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS likePublicacion(");
            sb.append("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("idUsuario INT NOT NULL,");
            sb.append("idPublicacion INT NOT NULL,");
            sb.append("FOREIGN KEY (idUsuario) REFERENCES Usuario(nick),");
            sb.append("FOREIGN KEY (idPublicacion) REFERENCES Publicacion(id));");

            sb.append("email VARCHAR (100) NOT NULL,");
            sb.append("facultad VARCHAR (100) NOT NULL,");
            sb.append("carrera VARCHAR (100) NOT NULL,");
            sb.append("FOREIGN KEY (email) REFERENCES juegoUsuario(email),");
            sb.append("FOREIGN KEY (facultad,carrera) REFERENCES usuario(facultad,carrera),");
            sb.append("PRIMARY KEY (email,facultad,carrera))");
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