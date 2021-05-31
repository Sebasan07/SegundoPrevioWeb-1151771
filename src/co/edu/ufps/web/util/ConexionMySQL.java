package co.edu.ufps.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionMySQL implements ConexionFactory {
	
	private Connection con = null;
	private static ConexionMySQL conexion;
	
	private static final String host = "localhost";
	private static final String dbName = "demo";
    private static final String url= "jdbc:mysql://"+host+":3306/"+dbName;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String userName = "root";
    private static final String password = "";

    public ConexionMySQL() {
		this.conectar();
	}

	public void conectar() {
		try {
			Class.forName(driver).newInstance();
			con = (Connection)DriverManager.getConnection(url,userName,password);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
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
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public static ConexionMySQL getConexion() {
    	if (conexion == null) {
    		conexion = new ConexionMySQL();
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
