package co.edu.ufps.web.util;

public class ConexionFactoryImpl  {

	public ConexionFactory getConexion(String tipo) {
		
		switch(tipo.toUpperCase()) {
		case "MYSQL":
			return new ConexionMySQL();
		case "POSTGRESQL":
			return new ConexionPostgreSQL();
		default:
			return null;
		}
	}
}
