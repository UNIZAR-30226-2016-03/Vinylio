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
        stmt.executeQuery("USE webps");
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
            sb.append("nick CHAR(64) NOT NULL PRIMARY KEY, ");
            sb.append("nombreYApellidos CHAR(64) NOT NULL,");
            sb.append("correo CHAR(64) NOT NULL UNIQUE ,");
            sb.append("contrasenya CHAR(20) NOT NULL,");
            sb.append("urlImagen CHAR(255),");
            sb.append("fechaDeNacimiento DATE NOT NULL,");
            sb.append("biografia CHAR(255),");
            sb.append("localizacion CHAR(32),");
            sb.append("urlTwitter CHAR(255),");
            sb.append("urlFacebook CHAR(255),");
            sb.append("urlPersonal CHAR(255));");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de juegos...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS juego(");
            sb.append("id INT PRIMARY KEY AUTO_INCREMENT,");
            sb.append("nombre CHAR(64) NOT NULL,");
            sb.append("descripcion TEXT(10000) NOT NULL);");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de juegos/usuarios...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS juegoUsuario(");
            sb.append("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("idJuego INT NOT NULL,");
            sb.append("nick CHAR(64) NOT NULL,");
            sb.append("FOREIGN KEY (nick) REFERENCES usuario(nick) ON DELETE CASCADE,");
            sb.append("FOREIGN KEY (idJuego) REFERENCES juego(id) ON DELETE CASCADE);");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de amistades...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS amistades(");

            sb.append("nickUsuarioPrimero INT REFERENCES usuario(nick) NOT NULL,");
            sb.append("nickUsuarioSegundo INT REFERENCES usuario(nick) NOT NULL,");
            sb.append("aceptado BOOLEAN NOT NULL DEFAULT FALSE,");
            sb.append("PRIMARY KEY (nickUsuarioPrimero, nickUsuarioSegundo));");

            System.out.println("Creando la tabla de publicaciones...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS publicacion(");
            sb.append("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("texto CHAR(255) NOT NULL,");
            sb.append("nick CHAR(64) NOT NULL,");
            sb.append("FOREIGN KEY (nick) REFERENCES usuario(nick) ON DELETE CASCADE);");

            mt.executeSentence(sb.toString());

            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de actividades...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS actividad(");
            sb.append("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("descripcion CHAR(128) NOT NULL,");
            sb.append("idJuego INT NOT NULL,");
            sb.append("FOREIGN KEY (idJuego) REFERENCES Juego(id));");

            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de usuarios/actividades...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS participaActividad(");
            sb.append("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("nickUsuario CHAR(64) NOT NULL,");
            sb.append("idActividad INT NOT NULL,");
            sb.append("FOREIGN KEY (nickUsuario) REFERENCES usuario(nick),");
            sb.append("FOREIGN KEY (idActividad) REFERENCES Actividad(id));");
            mt.executeSentence(sb.toString());


            System.out.println("Creando la tabla de likes de actividades...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS likeActividad(");
            sb.append("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("nickUsuario CHAR(64) NOT NULL,");
            sb.append("idActividad INT NOT NULL,");
            sb.append("FOREIGN KEY (nickUsuario) REFERENCES usuario(nick),");
            sb.append("FOREIGN KEY (idActividad) REFERENCES actividad(id));");
            mt.executeSentence(sb.toString());



            System.out.println("Creando la tabla de likes de publicaciones...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS likePublicacion(");
            sb.append("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,");
            sb.append("nick CHAR(64) NOT NULL,");
            sb.append("idPublicacion INT NOT NULL,");
            sb.append("FOREIGN KEY (nick) REFERENCES usuario(nick),");
            sb.append("FOREIGN KEY (idPublicacion) REFERENCES publicacion(id));");
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