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
                + " (nick,nombreYApellidos,correo,contrasenya,urlImagen,"
                + "fechaDeNacimiento,biografia,localizacion,urlTwitter,"
                + "urlFacebook, urlPersonal) VALUES ('"
                + user.getNick() + "', '" + user.getNombreApellidos() + "', '"
                + user.getCorreo() + "', '" + user.getPassword() + "', '"
                + user.getUrlImagen() + "', '" + user.getFechaNacimiento() + "', '"
                + user.getBiografia() + "', '" + user.getLocalizacion() + "', '"
                + user.getUrlTwitter() + "', '"
                + user.getUrlFacebook()  + "', '" + user.getUrlPersonal()+ "')");
        stmt.close();
        disconnect();
    }

    public void delete(Usuario user) throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM usuario WHERE correo='" + user.getCorreo() + "'");
        stmt.close();
        disconnect();
    }

    public void updateUser(Usuario user, String campo, String valor) throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("UPDATE usuario SET " + campo + " = '" +valor
                + "' WHERE correo = '" + user.getCorreo() + "'");
        stmt.close();
        disconnect();
    }

    public Usuario getUserNick(String nick) throws SQLException {
        Usuario user = null;
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE nick='" + nick + "'");
        if(rs.next()){
            String nombreYApellidos = rs.getString("nombreYApellidos");
            String email = rs.getString("correo");
            String password = rs.getString("contrasenya");
            String urlImagen = rs.getString("urlImagen");
            String fechaDeNacimiento = rs.getString("fechaDeNacimiento");
            String biografia = rs.getString("biografia");
            String localizacion = rs.getString("localizacion");
            String urlTwitter = rs.getString("urlTwitter");
            String urlFacebook = rs.getString("urlFacebook");
            String urlPersonal = rs.getString("urlPersonal");

            user = new Usuario(nick,nombreYApellidos,email,password,urlImagen,
                    fechaDeNacimiento,localizacion,biografia,urlTwitter,
                    urlFacebook,urlPersonal);
        }
        stmt.close();
        disconnect();

        return user;
    }

    public Usuario getUserEmail(String email) throws SQLException {
        Usuario user = null;
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE correo='" + email + "'");
        if(rs.next()){
            String nombreYApellidos = rs.getString("nombreYApellidos");
            String nick = rs.getString("nick");
            String password = rs.getString("contrasenya");
            String urlImagen = rs.getString("urlImagen");
            String fechaDeNacimiento = rs.getString("fechaDeNacimiento");
            String biografia = rs.getString("biografia");
            String localizacion = rs.getString("localizacion");
            String urlTwitter = rs.getString("urlTwitter");
            String urlFacebook = rs.getString("urlFacebook");
            String urlPersonal = rs.getString("urlPersonal");

            user = new Usuario(nick,nombreYApellidos,email,password,urlImagen,
                    fechaDeNacimiento,localizacion,biografia,urlTwitter,
                    urlFacebook,urlPersonal);
        }
        stmt.close();
        disconnect();

        return user;
    }

    public String obtenerMiniatura(Usuario user) throws SQLException{
        String urlImagen = "";
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE correo='" + user.getCorreo() + "'");
        if(rs.next()){
            urlImagen = rs.getString("urlImagen");
        }
        stmt.close();
        disconnect();

        return urlImagen;
    }

    public void anhadirAmistad(Usuario user1, Usuario user2) throws SQLException{

    }

    public void aceptarAmistad(Usuario user1, Usuario user2) throws SQLException{

    }

}

