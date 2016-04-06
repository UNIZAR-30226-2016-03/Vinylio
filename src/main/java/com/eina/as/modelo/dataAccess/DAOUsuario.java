package com.eina.as.modelo.dataAccess;

import com.eina.as.modelo.service.*;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Fran Menendez Moya on 5/4/16.
 */
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
        stmt.executeQuery("USE webAS");
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

    /*public void insert(Usuario user) throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        //TODO: FALTA PONER NOMBRE DE LA TABLA Y LOS CAMPOS ADECUADOS
        int res = stmt.executeUpdate("INSERT INTO  nombreTabla"
                + " (EMAIL,PASSWORD,NOMBRE,APELLIDOS,ADMIN,ANNO) VALUES ('"
                + user.getEmail() + "', '" + user.getPassword() + "', '"
                + user.getNombre() + "', '" + user.getApellidos() + "', '" + user.getAdmin()  + "', '" + anno+ "')");
        stmt.close();
        disconnect();
    }

    public void delete(Usuario user) throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        //TODO: FALTA PONER NOMBRE DE LA TABLA Y LOS CAMPOS ADECUADOS
        int res = stmt.executeUpdate("DELETE FROM  WHERE EMAIL='" + mail + "'");
        stmt.close();
        disconnect();
    }

    public void updateUser(Usuario user, String campo, String valor) throws SQLException{
        if(getUser(user.getEmail()) != null || email.equals(null)){
            connect();
            Statement stmt = connection.createStatement();
            //TODO: FALTA PONER NOMBRE DE LA TABLA Y LOS CAMPOS ADECUADOS
            int res=stmt.executeUpdate("UPDATE  SET NOMBRE = '" + user.getNombre()
                    + "', APELLIDOS = '" + user.getApellidos()
                    + "', PASSWORD = '" + user.getPassword()
                    + "', EMAIL = '" + user.getEmail()
                    + "' WHERE EMAIL = '" + email + "'");
            stmt.close();
            disconnect();
        }
    }

    public Usuario getUser(String nick) throws SQLException {
        Usuario user = null;
        connect();
        Statement stmt = connection.createStatement();
        //TODO: FALTA PONER NOMBRE DE LA TABLA Y LOS CAMPOS ADECUADOS
        ResultSet rs = stmt.executeQuery("SELECT * FROM  WHERE EMAIL='" + mail + "'");
        if(rs.next()){
            String nombre = rs.getString("NOMBRE");
            String apellidos = rs.getString("APELLIDOS");
            String email = rs.getString("EMAIL");
            String password = rs.getString("PASSWORD");
            Integer admin = rs.getInt("ADMIN");
            Integer anno = rs.getInt("ANNO");

            user = new Usuario(email,password,nombre,apellidos,admin,anno);
        }
        stmt.close();
        disconnect();

        return user;
    }

    public String obtenerMiniatura(Usuario user) throws SQLException{
        String url = "";
        connect();
        Statement stmt = connection.createStatement();
        //TODO: FALTA PONER NOMBRE DE LA TABLA Y LOS CAMPOS ADECUADOS
        ResultSet rs = stmt.executeQuery("SELECT * FROM  WHERE EMAIL='" + mail + "'");
        if(rs.next()){
            url = rs.getString("");

        }
        stmt.close();
        disconnect();

        return url;
    }

    public void anhadirAmistad(Usuario user1, Usuario user2) throws SQLException{

    }

    public void aceptarAmistad(Usuario user1, Usuario user2) throws SQLException{

    }*/

}
