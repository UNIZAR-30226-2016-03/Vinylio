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

            mt.executeSentence("DROP TABLE IF EXISTS Colecciona");
            mt.executeSentence("DROP TABLE IF EXISTS Usuario");
            mt.executeSentence("DROP TABLE IF EXISTS Vinilo");



            System.out.println("Creando la tabla de usuarios...");
            sb.append("CREATE TABLE IF NOT EXISTS Usuario (");
            sb.append("id_usuario   INTEGER PRIMARY KEY AUTO_INCREMENT, ");
            sb.append("email VARCHAR(50) NOT NULL UNIQUE,");
            sb.append("nombreApellidos   VARCHAR(100) NOT NULL,");
            sb.append("biografia    VARCHAR(255),");
            sb.append("URL_foto VARCHAR(255),");
            sb.append("nacimiento VARCHAR(20),");
            sb.append("lugar    VARCHAR(255),");
            sb.append("password VARCHAR(255));");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de vinilos...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS Vinilo (");
            sb.append("id_vinilo   INTEGER PRIMARY KEY AUTO_INCREMENT, ");
            sb.append("titulo   VARCHAR(255) NOT NULL,");
            sb.append("autor    VARCHAR(30) NOT NULL,");
            sb.append("genero   VARCHAR(255),");
            sb.append("fecha	INTEGER,");
            sb.append("discografica VARCHAR(255),");
            sb.append("imagen   VARCHAR(255))");
            mt.executeSentence(sb.toString());

            System.out.println("Creando la tabla de colecciones...");
            sb = new StringBuffer();
            sb.append("CREATE TABLE IF NOT EXISTS Colecciona(");
            sb.append("id_user INT NOT NULL,");
            sb.append("id_vinilo INT NOT NULL,");
            sb.append("FOREIGN KEY (id_user) REFERENCES Usuario(id_usuario) ON DELETE CASCADE,");
            sb.append("foreign key (id_vinilo) REFERENCES Vinilo(id_vinilo) ON DELETE CASCADE);");
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