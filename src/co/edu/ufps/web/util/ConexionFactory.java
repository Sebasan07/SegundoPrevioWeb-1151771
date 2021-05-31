package co.edu.ufps.web.util;

import java.sql.ResultSet;

public interface ConexionFactory {

	public void conectar();
	public ResultSet consultar(String sql);
	public void cerrarConexion();
}
