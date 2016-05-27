package com.eina.as.modelo.dataAccess;

import com.eina.as.modelo.service.*;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DAOUsuario {

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
    public DAOUsuario() throws InstantiationException,IllegalAccessException, ClassNotFoundException, SQLException{

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

    public void insert(Usuario user) throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO  usuario"
                + " (email,nombreApellidos,biografia,URL_foto,"
                + "nacimiento,lugar,password) VALUES ('"
                + user.getEmail() + "', '"
                + user.getNombreApellidos() + "', '" + user.getBiografia() + "', '"
                + user.getUrlFoto() + "', '" + user.getNacimiento() + "', '"
                + user.getlugar() + "', '" + user.getPassword() + "')");
        stmt.close();
        disconnect();
    }

    public void delete(Usuario user) throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM usuario WHERE email='" + user.getEmail() + "'");
        stmt.close();
        disconnect();
    }

    public void updateUser(Usuario user, String campo, String valor) throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("UPDATE usuario SET " + campo + " = '" +valor
                + "' WHERE email = '" + user.getEmail() + "'");
        stmt.close();
        disconnect();
    }

    public boolean existe(Usuario user) throws SQLException {
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE email = '" + user.getEmail() + "'");
        boolean existe = rs.first();
        stmt.close();
        disconnect();
        return existe;
    }


    public Usuario getUserEmail(String email) throws SQLException {
        Usuario user = null;
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE email='" + email + "'");
        if(rs.next()){
            String nombreApellidos = rs.getString("nombreApellidos");
            String password = rs.getString("password");
            String URL_foto = rs.getString("URL_foto");
            String nacimiento = rs.getString("nacimiento");
            String biografia = rs.getString("biografia");
            String lugar = rs.getString("lugar");
            int id = rs.getInt("id_usuario");

            user = new Usuario(nacimiento,nombreApellidos,email,password,URL_foto,
                    biografia,lugar,id);
        }
        stmt.close();
        disconnect();

        return user;
    }

    public Usuario getUserNombreApellidos(String nombreApellidos) throws SQLException {
        Usuario user = null;
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE nombreApellidos='" + nombreApellidos + "'");
        if(rs.next()){
            String email = rs.getString("email");
            String password = rs.getString("password");
            String URL_foto = rs.getString("URL_foto");
            String nacimiento = rs.getString("nacimiento");
            String biografia = rs.getString("biografia");
            String lugar = rs.getString("lugar");

            user = new Usuario(nacimiento,nombreApellidos,email,password,URL_foto,
                    biografia,lugar);
        }
        stmt.close();
        disconnect();

        return user;
    }
}