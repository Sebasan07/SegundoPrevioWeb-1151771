package co.edu.ufps.web.DAO;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T, ID> {

	public List<T> list() throws SQLException;
	public boolean insertar(T info) throws SQLException;
	public boolean actualizar(T info) throws SQLException;
	public T buscar(ID id) throws SQLException;
	public boolean eliminar(ID id) throws SQLException;
	
}
