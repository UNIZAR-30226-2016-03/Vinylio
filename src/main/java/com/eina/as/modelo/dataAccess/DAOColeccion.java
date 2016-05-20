package com.eina.as.modelo.dataAccess;

import com.eina.as.modelo.service.Usuario;
import com.eina.as.modelo.service.Vinilo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class DAOColeccion {

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


    public DAOColeccion() throws InstantiationException,IllegalAccessException, ClassNotFoundException, SQLException{

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

    /**
     *
     * @param user
     * @param vinilo
     * @throws SQLException
     */
    public void insert(Usuario user, Vinilo vinilo) throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO  colecciona"
                + " (id_user, id_vinilo) VALUES ('"
                + user.getIdUsuario() + "', '"
                + vinilo.getIdVinilo()
                + "')");
        stmt.close();
        disconnect();
    }

    /**
     *
     * @param user
     * @param vinilo
     * @throws SQLException
     */
    public void delete(Usuario user, Vinilo vinilo) throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM colecciona WHERE id_vinilo='" + vinilo.getIdVinilo() + "' AND id_user='" + user.getIdUsuario() +"'" );
        stmt.close();
        disconnect();
    }

    /**
     *
     * @param user
     * @return ArrayList<Vinilo>
     * @throws SQLException
     */
    public ArrayList<Vinilo> getListaVinilos(Usuario user) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM colecciona WHERE id_user='" + user.getIdUsuario() +"'");
        ArrayList<Vinilo> historial = new ArrayList<Vinilo>();
        DAOVinilo daoVinilo = new DAOVinilo();
        while(rs.next()){
            Vinilo aux = daoVinilo.getViniloByID(rs.getInt("id_vinilo"));
            /*
            aux.setIdVinilo(rs.getInt("id_vinilo"));
            aux.setTitulo(rs.getString("titulo"));
            aux.setAutor(rs.getString("autor"));
            aux.setGenero(rs.getString("genero"));
            aux.setFecha(rs.getInt("fecha"));
            aux.setDiscografica(rs.getString("discografica"));
            aux.setImagen(rs.getString("imagen"));
            aux.setRPM(rs.getInt("RPM"));
            aux.setNumLanzamiento(rs.getString("numLanzamiento"));
            */
            historial.add(aux);
        }
        stmt.close();
        disconnect();

        return historial;
    }

    /**
     *
     * @param user
     * @return int
     * @throws SQLException
     */
    public int getNumeroVinilos(Usuario user) throws SQLException {
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM colecciona WHERE id_user='" + user.getIdUsuario() + "'");
        int i = 0;
        while(rs.next()){
            i++;
        }
        stmt.close();
        disconnect();

        return i;
    }

    public boolean existe(Vinilo vin) throws SQLException {
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM colecciona WHERE id_vinilo='" + vin.getIdVinilo() + "'");
        boolean existe = rs.first();
        stmt.close();
        disconnect();
        return existe;
    }
}