package com.eina.as;

import com.eina.as.modelo.dataAccess.DAOUsuario;
import com.eina.as.modelo.dataAccess.DAOVinilo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.eina.as.modelo.service.*;
import java.sql.*;
import java.util.Locale;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ASApplication.class)
@WebAppConfiguration
public class ASApplicationTests {

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


	public ASApplicationTests() throws InstantiationException,IllegalAccessException, ClassNotFoundException, SQLException{

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


	@Test
	public void contextLoads() {
	}

	/**
	 * Inserta 1500 valores de coleccion de vinilos (id usuario e id vinilo)
	 * Resultado: Satisfactorio
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
     */
	@Test
	public void sobrecargaColeccion() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		connect();
		Statement stmt = connection.createStatement();
		DAOUsuario daoUsuario = new DAOUsuario();
		DAOVinilo daoVinilo = new DAOVinilo();
		for (int i=0;i<1499;i++) {
			Usuario user = daoUsuario.getUserEmail("email"+i);
			Vinilo vinilo = daoVinilo.getViniloTitulo("titulo"+(i+1));
			stmt.executeUpdate("INSERT INTO  colecciona"
					+ " (id_user, id_vinilo) VALUES ('"
					+ user.getIdUsuario() + "', '"
					+ vinilo.getIdVinilo()
					+ "')");
		}
		stmt.close();
		disconnect();
	}


	/**
	 * Inserta 1500 vinilos en la base de datos para probar el comportamiento con grandes cantidades de datos.
	 * Resultado: Satisfactorio
	 * @throws SQLException
     */
	@Test
	public void sobrecargaCatalogo() throws SQLException{
		connect();
		Statement stmt = connection.createStatement();
		for (int i=0;i<1499;i++) {
			Vinilo vinilo = new Vinilo("titulo"+i,"autor"+i,"",0,"","imagen"+i,0,"");
			stmt.executeUpdate("INSERT INTO  Vinilo"
					+ " (titulo,autor,genero,fecha,"
					+ "discografica,imagen) VALUES ('"
					+ vinilo.getTitulo() + "', '" + vinilo.getAutor() + "', '"
					+ vinilo.getGenero() + "', '" + vinilo.getFecha() + "', '"
					+ vinilo.getDiscografica() + "', '" + vinilo.getImagen()
					+ "');");
		}
		stmt.close();
		disconnect();
	}

	/**
	 * Inserta 1500 usuarios en la base de datos para probar el comportamiento con grandes cantidades de datos.
	 * Resultado: Satisfactorio
	 * @throws SQLException
	 */
	@Test
	public void sobrecargaUsuarios() throws SQLException{
		connect();
		Statement stmt = connection.createStatement();
		for (int i=0;i<1499;i++) {
			Usuario user = new Usuario("email"+i,"password"+i,"nombre"+i,"imagen"+i);
			stmt.executeUpdate("INSERT INTO  usuario"
					+ " (email,nombreApellidos,biografia,URL_foto,"
					+ "nacimiento,lugar,password) VALUES ('"
					+ user.getEmail() + "', '"
					+ user.getNombreApellidos() + "', '" + user.getBiografia() + "', '"
					+ user.getUrlFoto() + "', '" + user.getNacimiento() + "', '"
					+ user.getlugar() + "', '" + user.getPassword() + "')");
		}
		stmt.close();
		disconnect();
	}

}
