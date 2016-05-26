package com.eina.as.modelo.dataAccess;

import com.eina.as.modelo.service.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DAOVinilo {

    /**
     * Driver para conectar con MYSQL
     */
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    /**
     * Conexion a una BD MYSQL
     */
    private static final String DB_URL = "jdbc:mysql://vinylio.csvlc89bx3ln.eu-central-1.rds.amazonaws.com:3306";

    /**
     * CaDena de caracteres con el nombre de usuario, o login, a emplear para
     * conectarse a la BD
     */
    private static final String USER = "user";
    /**
     * Cadena de caracteres con el password, o contraseña, a emplear para
     * conectarse a la BD
     */
    private static final String PASS = "cristiandimision";
    /**
     * Conexion con la BD
     */
    private Connection connection = null;


    public DAOVinilo() throws InstantiationException,IllegalAccessException, ClassNotFoundException, SQLException{

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

    public void insert(Vinilo vinilo) throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO  Vinilo"
                + " (id_vinilo,titulo,autor,genero,fecha,"
                + "discografica,imagen) VALUES ('"
                + vinilo.getIdVinilo() + "', '"
                + vinilo.getTitulo() + "', '" + vinilo.getAutor() + "', '"
                + vinilo.getGenero() + "', '" + vinilo.getFecha() + "', '"
                + vinilo.getDiscografica() + "', '" + vinilo.getImagen() + "', '"
                + "')");
        stmt.close();
        disconnect();
    }

    public void delete(Vinilo vinilo) throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM Vinilo WHERE titulo='" + vinilo.getTitulo() + "'");
        stmt.close();
        disconnect();
    }

    public void updateVinilo(Vinilo vinilo, String campo, String valor) throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("UPDATE vinilo SET " + campo + " = '" +valor
                + "' WHERE titulo = '" + vinilo.getTitulo() + "'");
        stmt.close();
        disconnect();
    }

    public Vinilo getViniloTitulo(String titulo) throws SQLException {
        Vinilo vinilo = null;
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Vinilo WHERE titulo = '"+titulo+"';");
        if(rs.next()){
            int id_vinilo = rs.getInt("id_vinilo");
            String autor = rs.getString("autor");
            String genero = rs.getString("titulo");
            int fecha = rs.getInt("fecha");
            String discografica = rs.getString("discografica");
            String imagen = rs.getString("imagen");
            int RPM = rs.getInt("RPM");
            String numLanzamiento = rs.getString("numLanzamiento");

            vinilo = new Vinilo(id_vinilo,titulo,autor,genero,fecha,discografica,imagen, RPM, numLanzamiento);
        }
        stmt.close();
        disconnect();

        return vinilo;
    }

// me dará la lista de 25 vinilos que se encuentren en la pagina x, es decir si queremos los 25 primeros la pagina será
    // la 0, si queremos que salgan los de la 3ra pagina entonces pondrémos un 2.
    // Duda: No se si el primer elemento o el ultimo salen seguro. 0-24 o de 1-25?
    // Duda: Al hacer rs.absolute(j) si no hay tantos elementos en la base de datos que pasa? Sería mejor directamente
    //      coger en el select de la base de datos a partir del dato que me interesa en vez de cargarlos todos y luego
    //      moverme con el absolute(j). Podría dar error tambien la consulta si me quiero mover a un numero de elemento
    //      que no hay?
    public ArrayList<Vinilo> getListaVinilos(int pagina, String tipoOrdenacion) throws SQLException {

        ArrayList<Vinilo> historial= new ArrayList<Vinilo>();
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs;
        if(tipoOrdenacion.equalsIgnoreCase("fecha")){
            rs = stmt.executeQuery("SELECT * FROM Vinilo ORDER BY fecha");
        }
        else{
            rs = stmt.executeQuery("SELECT * FROM Vinilo ORDER BY titulo");
        }
        int j=pagina*25;
        int i=0;
        ArrayList <Vinilo> listaVinilos =  new ArrayList<Vinilo>();
        rs.absolute(j); // desplaza j vinilos de rs
        while(rs.next() && i<25){
            Vinilo aux = new Vinilo(0,"","","",0,"","",33,"");
            aux.setIdVinilo(rs.getInt("id_vinilo"));
            aux.setTitulo(rs.getString("titulo"));
            aux.setAutor(rs.getString("autor"));
            aux.setGenero(rs.getString("genero"));
            aux.setFecha(rs.getInt("fecha"));
            aux.setDiscografica(rs.getString("discografica"));
            aux.setImagen(rs.getString("imagen"));
            aux.setRPM(rs.getInt("RPM"));
            aux.setNumLanzamiento(rs.getString("numLanzamiento"));

            historial.add(aux);
            i++;
        }
        stmt.close();
        disconnect();

        return historial;
    }

    public int getNumeroVinilos() throws SQLException {
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(id_vinilo) jose FROM Vinilo");
        int i =0;
        if(rs.next()) {
            String ayy = rs.getString("jose");
            i = Integer.parseInt(ayy);
        }
        else i=0;
        /*while(rs.next()){
            i++;
        }*/
        stmt.close();
        disconnect();

        return i;
    }

    public Vinilo getViniloByID(int idVinilo) throws SQLException{
        Vinilo vinilo = null;
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Vinilo WHERE id_vinilo='"+idVinilo+"'");
        if(rs.next()){
            int id_vinilo = idVinilo;
            String titulo = rs.getString("titulo");
            String autor = rs.getString("autor");
            String genero = rs.getString("genero");
            int fecha = rs.getInt("fecha");
            String discografica = rs.getString("discografica");
            String imagen = rs.getString("imagen");
            int RPM = rs.getInt("RPM");
            String numLanzamiento = rs.getString("numLanzamiento");

            vinilo = new Vinilo(id_vinilo,titulo,autor,genero,fecha,discografica,imagen, RPM, numLanzamiento);
        }
        stmt.close();
        disconnect();

        return vinilo;
    }



/*
    public String obtenerAutor(String vinilo) throws SQLException{
        String nombreAutor = "";
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Vinilo WHERE titulo='" + vinilo + "'");
        if(rs.next()){
            nombreAutor = rs.getString("autor");
        }
        stmt.close();
        disconnect();

        return nombreAutor;
    }

    public String obtenerFoto(String vinilo) throws SQLException{
        String URL_foto = "";
        connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Vinilo WHERE titulo REGEXP '"+vinilo+"'");
        if(rs.next()){
            URL_foto = rs.getString("imagen");
        }
        stmt.close();
        disconnect();

        return URL_foto;
    }
   */
}