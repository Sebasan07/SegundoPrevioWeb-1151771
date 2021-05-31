package co.edu.ufps.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionPostgreSQL implements ConexionFactory {

	private Connection con = null;
	private static ConexionPostgreSQL conexion;

	private static final String host = "queenie.db.elephantsql.com";
	private static final String dbName = "mnjgxshj";
	private static final String url = "jdbc:postgresql://" + host + ":5432/" + dbName;
	private static final String driver = "org.postgresql.Driver";
	private static final String userName = "mnjgxshj";
	private static final String password = "Uzjqo00sxV0W9OzPEB1q3wpoVvGMbbUV";

	public ConexionPostgreSQL() {
		this.conectar();
	}

	@Override
	public void conectar() {
		try {
			Class.forName(driver).newInstance();
			con = (Connection) DriverManager.getConnection(url, userName, password);

			boolean valid = con.isValid(50000);
			System.out.println(valid ? "TEST OK" : "TEST FAIL");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ConexionPostgreSQL getConexion() {
		if (conexion == null) {
			conexion = new ConexionPostgreSQL();
		}

		return conexion;
	}

	public ResultSet consultar(String sql) {

		Statement st;
		try {
			st = this.con.createStatement();

			ResultSet res = st.executeQuery(sql);

			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
